package server;

import tcp.CommunicationThread;

import java.io.IOException;
import java.net.*;
import java.sql.SQLException;

public class Server {
    public static void main(String[] args) {

        ServerSocket serverSocket = null;

        try {
            // 1 - pubblicare una ServerSocket, decidendo una porta NON RISERVATA
            serverSocket = new ServerSocket(5001);

            while (true) {
                // 2 - mettersi in attesa di ricevere richieste dai client, ed accettarle
                // METODO BLOCCANTE

                System.out.println("Sono in attesa di accettare un client");

                Socket comunicationSocketFromServer = serverSocket.accept();

                System.out.println("Client accettato");

                CommunicationThread comunicationThread = new CommunicationThread(comunicationSocketFromServer);

                comunicationThread.start();
            }
        } catch (IOException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
            }
        }

    }
}
