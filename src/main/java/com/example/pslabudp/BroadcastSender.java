package com.example.pslabudp;

import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;

import static com.example.pslabudp.UDPController.BROADCAST;

public class BroadcastSender {
    private DatagramSocket socket;
    private byte[] buf;
    private final UDPController c;

    public BroadcastSender(UDPController c) {
        this.c = c;
    }

    public void send(String message) throws IOException {
        socket = new DatagramSocket();
        socket.setBroadcast(true);
        String m = BROADCAST + message;
        buf = m.getBytes();

        InetAddress address = InetAddress.getByName(c.getIp());
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, c.getPort());

        socket.send(packet);
        socket.close();
    }
}
