package server;

import mySql.GestoreAsta;
import udp.StanzaAsta;
import udp.Udp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UdpServer {

    public static void main(String[] args) throws IOException, InterruptedException, SQLException {
        GestoreAsta gestoreAsta = new GestoreAsta();
        ArrayList<String> ipList = gestoreAsta.getIpMultiCast();
        Udp udp = new Udp();
        while (true) {
            String ip = udp.receiveUDPMessage("localhost", 4322);
            if (ipList.contains(ip)) {
                new StanzaAsta(ip).start();
                ipList.remove(ip);
                Thread.sleep(450);
                udp.sendMulticastUDPMessage("stanza inizializzata", ip, 4321);
            }

        }
        //udp.receiveMulticastUDPMessage("230.0.0.0", 4321);
    }


}

