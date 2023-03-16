package client;

import java.io.*;
import java.net.Socket;

public class App {
    public static void main(String[] args) {

        Socket comunicationSocketFromClient = null;

        try {
            // 1 - crea una socket con i riferimenti del server
            comunicationSocketFromClient = new Socket("localhost", 5000);


            OutputStream outputStreamClient = comunicationSocketFromClient.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStreamClient);

            InputStream inputStreamClient = comunicationSocketFromClient.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamClient));

            String messaggio = "cippa";

            do {
                dataOutputStream.writeBytes(messaggio + "\n");

                System.out.println("Sono in attesa di una risposta dal server");

                // METODO BLOCCANTE
                String risposta = bufferedReader.readLine();

                System.out.println("Ricevuto dal server: " + risposta);
            } while (!messaggio.equals("esco"));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        if(comunicationSocketFromClient != null) {
            try {
                comunicationSocketFromClient.close();
            } catch (IOException e) {

            }
        }

    }

}
