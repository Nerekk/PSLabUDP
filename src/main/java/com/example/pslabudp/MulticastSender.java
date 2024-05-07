package com.example.pslabudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static com.example.pslabudp.UDPController.MULTICAST;

public class MulticastSender {
    private DatagramSocket socket;
    private InetAddress group;
    private byte[] buf;
    private final UDPController c;

    public MulticastSender(UDPController c) {
        this.c = c;
    }

    public void send(String message) throws IOException {
        socket = new DatagramSocket();
        group = InetAddress.getByName(c.getIp());
        String m = MULTICAST + message;
        buf = m.getBytes();

        DatagramPacket packet = new DatagramPacket(buf, buf.length, group, c.getPort());
        socket.send(packet);
        socket.close();
    }
}
