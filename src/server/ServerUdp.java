package server;

import mySql.GestoreAsta;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;

public class ServerUdp{
    public static void main(String[] args) throws Exception {
        int mcPort = 12345;
        String mcIPStr = "230.1.1.1";
        MulticastSocket mcSocket = null;
        InetAddress mcIPAddress = null;

        GestoreAsta gestoreAsta = new GestoreAsta();

        ArrayList<String> listaIp = gestoreAsta.selectAllObjects();
        ArrayList<InetAddress> addresses = new ArrayList<>();
        int index = 0;
        for (String ip : listaIp) {
            addresses.add(InetAddress.getByName(ip));
            addresses.get(index).joinGroup(addresses);
            index++;
        }
        System.out.println("Multicast Receiver running at:"
                + mcSocket.getLocalSocketAddress());
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

        System.out.println("Waiting for a  multicast message...");
        mcSocket.receive(packet);
        String msg = new String(packet.getData(), packet.getOffset(),
                packet.getLength());
        System.out.println("[Multicast  Receiver] Received:" + msg);
        mcSocket.leaveGroup(mcIPAddress);
        mcSocket.close();
    }
}
