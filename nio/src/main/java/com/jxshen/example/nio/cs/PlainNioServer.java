package com.jxshen.example.nio.cs;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * reference from: Netty In Action
 */
public class PlainNioServer {
    
    public void server(int port) throws Exception {
        System.out.println(String.format("Listening for connections on port [%d]", port));
        Selector selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(port));
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        final ByteBuffer msg = ByteBuffer.wrap("Hello!\r\n".getBytes());
        while (true) {
            int n = selector.select();
            if (n > 0) {
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    iter.remove();
                    try {
                        if (key.isAcceptable()) {
                            ServerSocketChannel server = (ServerSocketChannel)key.channel();
                            SocketChannel client = server.accept();
                            System.out.println(String.format("Accepted connetion from [%s]", client));
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_WRITE, msg.duplicate());
                        }
                        if (key.isWritable()) {
                            SocketChannel client = (SocketChannel)key.channel();
                            ByteBuffer buff = (ByteBuffer)key.attachment();
                            while (buff.hasRemaining()) {
                                if (client.write(buff) == 0) {
                                    break;
                                }
                            }
                            client.close();
                        }
                    } catch (Exception e) {
                        key.cancel();
                        key.channel().close();
                    }
                }
            }
        }
    }
}
