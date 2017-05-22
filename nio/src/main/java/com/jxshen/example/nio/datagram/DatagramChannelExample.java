package com.jxshen.example.nio.datagram;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelExample {

    public static void receive() {
        DatagramChannel channel = null;
        try {
            channel = DatagramChannel.open();
            channel.bind(new InetSocketAddress(9998));
            ByteBuffer buf = ByteBuffer.allocate(1024);
            buf.clear();
            channel.receive(buf);
            
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char)buf.get());
            }
            System.out.println();
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
            } catch (IOException e) {
                
            }
        }
    }
    
    public static void send() {
        DatagramChannel channel = null;
        try {
            channel = DatagramChannel.open();
            String info = "I'm the Sender!";
            ByteBuffer buf = ByteBuffer.allocate(1024);
            buf.put(info.getBytes());
            int bytesSend = channel.send(buf, new InetSocketAddress("localhost", 9998));
            System.out.println(bytesSend);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
            } catch (IOException e) {
                
            }
        }
    }
}
