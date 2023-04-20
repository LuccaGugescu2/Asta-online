package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Udp {
    //224-239.0.0.0
    public void sendMulticastUDPMessage(String message, String ipAddress, int port) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress group = InetAddress.getByName(ipAddress);
        byte[] msg = message.getBytes();
        DatagramPacket packet = new DatagramPacket(msg, msg.length, group, port);
        socket.send(packet);
        socket.close();
    }

    public String receiveMulticastUDPMessage(String ip, int port) throws IOException {
        byte[] buffer = new byte[1024];
        MulticastSocket socket = new MulticastSocket(port);
        InetAddress group = InetAddress.getByName(ip);
        socket.joinGroup(group);
        System.out.println("Waiting for multicast message...");
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String msg = new String(packet.getData(), packet.getOffset(), packet.getLength());
        System.out.println("[Multicast UDP message received] >> " + msg);
        socket.leaveGroup(group);
        socket.close();
        return msg;
    }

    public void sendUDPMessage(String message, String ipAddress, int port) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        byte[] msg = message.getBytes();
        InetAddress address = InetAddress.getByName(ipAddress);
        DatagramPacket packet = new DatagramPacket(msg, msg.length, address, port);
        socket.send(packet);
        socket.close();
    }

    public String receiveUDPMessage(String ip, int port) throws IOException {
        byte[] buffer = new byte[1024];
        DatagramSocket socket = new DatagramSocket(port);
        System.out.println("Waiting for multicast message...");
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String msg = new String(packet.getData(), packet.getOffset(), packet.getLength());
        System.out.println("[Multicast UDP message received] >> " + msg);
        socket.close();
        return msg;
    }
}
