package com.gupaoedu.vip.nio;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class NIOClient implements Runnable {
    private InetSocketAddress localAddress = new InetSocketAddress("localhost", 8080);
    @Override
    public void run() {
        try {
            SocketChannel socketChannel = SocketChannel.open(localAddress);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketChannel.socket().getOutputStream()));
            writer.write("hello");
            writer.flush();
            System.out.println("client send message: hello");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
