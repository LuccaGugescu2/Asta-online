package tcp;

import mySql.Categoria;
import mySql.GestoreAsta;
import mySql.Oggetto;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommunicationThread extends Thread {

    private Socket socket;
    private GestoreAsta gestoreAsta;

    public CommunicationThread(Socket socket) throws SQLException {
        super();
        gestoreAsta = new GestoreAsta();
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStreamServer;
        ArrayList<Categoria> categorie = new ArrayList();
        try {
            inputStreamServer = socket.getInputStream();
            DataInputStream input = new DataInputStream(inputStreamServer);

            OutputStream outputStreamServer = socket.getOutputStream();

            DataOutputStream dataOutputStream = new DataOutputStream(outputStreamServer);
            categorie = gestoreAsta.getCategorie();

            dataOutputStream.writeInt(categorie.size());
            for(int i = 0; i < categorie.size(); i++) {
                dataOutputStream.writeInt(categorie.get(i).getId());
                dataOutputStream.writeUTF(categorie.get(i).getNome());
            }
            int categoriaSelezionata = input.readInt();
            ArrayList<Oggetto> oggetti = gestoreAsta.getOggettiByIdCategoria(categoriaSelezionata);
            dataOutputStream.writeInt(oggetti.size());
            for(int i = 0; i < oggetti.size(); i++) {
                dataOutputStream.writeInt(oggetti.get(i).getId());
                dataOutputStream.writeUTF(oggetti.get(i).getNome());
                dataOutputStream.writeInt(oggetti.get(i).getQuntità());
                dataOutputStream.writeInt(oggetti.get(i).getCategoria());
                dataOutputStream.writeFloat(oggetti.get(i).getBase_asta());
                dataOutputStream.writeUTF(oggetti.get(i).getIpMultiCast());
            }
        } catch (IOException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            gestoreAsta.close();
        } catch (SQLException e) {
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