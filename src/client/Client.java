package client;

import mySql.Categoria;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        Socket comunicationSocketFromClient = null;

        try {
            // 1 - crea una socket con i riferimenti del server
            comunicationSocketFromClient = new Socket("localhost", 5000);

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
            }
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
