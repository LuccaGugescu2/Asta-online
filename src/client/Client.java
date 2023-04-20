package client;

import jdk.dynalink.linker.LinkerServices;
import mySql.Categoria;
import mySql.Oggetto;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket comunicationSocketFromClient = null;
        List<Oggetto> oggetti = new ArrayList<>();

        try {
            // 1 - crea una socket con i riferimenti del server
            comunicationSocketFromClient = new Socket("localhost", 5001);

            OutputStream outputStreamClient = comunicationSocketFromClient.getOutputStream();
            DataOutputStream output = new DataOutputStream(outputStreamClient);
            InputStream inputStreamClient = comunicationSocketFromClient.getInputStream();
            DataInputStream input = new DataInputStream(inputStreamClient);

            int numeroCategorie;
            ArrayList<Categoria> categorie = new ArrayList<>();
            System.out.println("Sono in attesa di una risposta dal server");

            // METODO BLOCCANTE
            numeroCategorie = input.readInt();
            for (int i = 0; i < numeroCategorie; i++) {
                int idCategoria = input.readInt();
                String nomeCategoria = input.readUTF();
                System.out.print("id: " + idCategoria + ", nomeCategoria: " + nomeCategoria + "\n");

                categorie.add(new Categoria(idCategoria, nomeCategoria));
            }
            Scanner scan = new Scanner(System.in);
            System.out.println("----------INSERISCI ID CATEGORIA:--------------------");
            int choosed = scan.nextInt();
            output.writeInt(choosed);
            int oggettiSize = input.readInt();
            for (int i = 0; i < oggettiSize; i++) {
                int idOggetto = input.readInt();
                String nome = input.readUTF();
                int quantita = input.readInt();
                int id_categoria = input.readInt();
                float baseAsta = input.readFloat();
                String ipMultiCast = input.readUTF();
                Oggetto oggetto = new Oggetto(idOggetto,nome,quantita,id_categoria,baseAsta,ipMultiCast);
                oggetti.add(oggetto);
            }

            System.out.println("lista oggetti...");
            for (Oggetto e: oggetti) {
                System.out.println(e.toString());
            }

            System.out.println("seleziona un oggetto");
            int select = scan.nextInt();
            System.out.println(oggetti.get(select).stampaInformazioni());
            output.writeInt(select);

            int mcPort = 12345;
            String mcIPStr = oggetti.get(select).getIpMultiCast();
            DatagramSocket udpSocket = new DatagramSocket();
            InetAddress mcIPAddress = InetAddress.getByName(mcIPStr);
            String msgInput;
            do{
                System.out.println("fai un offerta");
                String offer = scan.next();
                output.writeUTF(offer);
                byte[] msg = offer.getBytes();
                DatagramPacket packet = new DatagramPacket(msg, msg.length);
                packet.setAddress(mcIPAddress);
                packet.setPort(mcPort);
                udpSocket.send(packet);
                DatagramPacket packetInput = new DatagramPacket(new byte[1024], 1024);
                msgInput = new String(packetInput.getData(), packetInput.getOffset(),
                        packetInput.getLength());
                System.out.println(msgInput);
            }while(msgInput.equals("esci"));
            udpSocket.close();
            System.out.println("Sent a  multicast message.");
            System.out.println("Exiting application");

            comunicationSocketFromClient.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (comunicationSocketFromClient != null) {
            try {
                comunicationSocketFromClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
