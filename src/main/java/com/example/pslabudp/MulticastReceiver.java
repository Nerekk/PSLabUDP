package com.example.pslabudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import static com.example.pslabudp.UDPController.*;

public class MulticastReceiver extends Thread{
    protected MulticastSocket socket = null;
    protected byte[] buf = new byte[256];
    private final UDPController c;
    private InetAddress group;

    public MulticastReceiver(UDPController c) {
        this.c = c;
    }

    public void connect(String ip, int port) throws IOException {
        socket = new MulticastSocket(port);
        group = InetAddress.getByName(ip);
        socket.joinGroup(group);


        Thread t = new Thread(this);
        t.start();
    }

    public void disconnect() throws IOException {
        socket.leaveGroup(group);
        socket.close();
    }

    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                isRunning = false;
                continue;
            }
            String received = new String(packet.getData(), 0, packet.getLength());
            int flag = Integer.parseInt(String.valueOf(received.charAt(0)));
            received = received.substring(1);
            switch (flag) {
                case MULTICAST:
                    c.sendAlert(MULTICAST, "Message [" + packet.getLength() + " bytes]: " + received);
                    break;
                case BROADCAST:
                    c.sendAlert(BROADCAST, "Message [" + packet.getLength() + " bytes]: " + received);
                    break;
                default:
                    c.sendAlert(INFO, "Message [" + packet.getLength() + " bytes]: " + received);
                    break;
            }
        }
    }
}
