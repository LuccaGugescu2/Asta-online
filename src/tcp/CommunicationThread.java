package tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class CommunicationThread extends Thread {

    private Socket socket;

    public CommunicationThread(Socket socket) {
        super();
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStreamServer;
        try {
            inputStreamServer = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamServer));

            OutputStream outputStreamServer = socket.getOutputStream();

            DataOutputStream dataOutputStream = new DataOutputStream(outputStreamServer);

            while(true) {
                System.out.println("Sono in attesa di un messaggio dal client");

                // METODO BLOCCANTE
                String messaggio = bufferedReader.readLine();

                System.out.println("Ricevuto dal client: " + messaggio);

                String risposta = messaggio.toUpperCase() + "\n";
                dataOutputStream.writeBytes(risposta);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}