package tcp;

import mySql.Categoria;
import mySql.GestoreAsta;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommunicationThread extends Thread {

    private Socket socket;
    private GestoreAsta gestoreAsta;

    public CommunicationThread(Socket socket) {
        super();
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStreamServer;
        ArrayList<Categoria> categorie = new ArrayList();
        try {
            inputStreamServer = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamServer));

            OutputStream outputStreamServer = socket.getOutputStream();

            DataOutputStream dataOutputStream = new DataOutputStream(outputStreamServer);
            gestoreAsta = new GestoreAsta();
            categorie = gestoreAsta.getCategorie();
            gestoreAsta.close();
            dataOutputStream.writeBytes(categorie.toString());

        } catch (IOException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}