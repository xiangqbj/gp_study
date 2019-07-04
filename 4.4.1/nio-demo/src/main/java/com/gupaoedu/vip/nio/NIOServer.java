package com.gupaoedu.vip.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer implements Runnable {
    private InetSocketAddress localAddress = new InetSocketAddress("localhost", 8080);

    @Override
    public void run() {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(localAddress, 200);
            serverSocketChannel.configureBlocking(false);

            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (!Thread.currentThread().isInterrupted()) {
                int selectedNum = selector.select();
                if (selectedNum > 0) {
                    Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                    Iterator<SelectionKey> it = selectionKeySet.iterator();
                    while (it.hasNext()) {
                        SelectionKey selectionKey = it.next();
                        it.remove();

                        if (selectionKey.isAcceptable()) {
                            ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
                            SocketChannel socketChannel = serverChannel.accept();
                            System.out.println("socket accepted");
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        } else if (selectionKey.isReadable()) {
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            socketChannel.read(buffer);
                            String result = new String(buffer.array(), 0, buffer.position());
                            System.out.println("server received: " + result);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
