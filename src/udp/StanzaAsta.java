package udp;

import java.io.IOException;

public class StanzaAsta extends Thread {

    private String ipMulticast;

    public StanzaAsta(String ipMulticast) {
        this.ipMulticast = ipMulticast;
    }

    @Override
    public void run() {
        Udp udp = new Udp();
        try {
            while (true) {

                System.out.println("thread partito");
                String msg = udp.receiveUDPMessage("localhost", 4322);
                udp.receiveMulticastUDPMessage(this.ipMulticast, 4321);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
