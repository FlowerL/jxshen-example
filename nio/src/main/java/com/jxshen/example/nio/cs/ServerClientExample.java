package com.jxshen.example.nio.cs;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * reference from: http://www.importnew.com/19816.html
 */
public class ServerClientExample {

    public void NioClient() {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        SocketChannel sc = null;
        try {
            sc = SocketChannel.open();
            sc.configureBlocking(false);
            sc.connect(new InetSocketAddress("localhost", 8080));
            
            if (sc.finishConnect()) {
                int i = 0;
                while (true) {
                    TimeUnit.SECONDS.sleep(1);
                    String info = String.format("I'm [%d]th information from client", i++);
                    buf.clear();
                    buf.put(info.getBytes());
                    buf.flip();
                    while (buf.hasRemaining()) {
                        System.out.println(buf);
                        sc.write(buf);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (sc != null) {
                    sc.close();
                }
            } catch(IOException e) {
                
            }
        }
    }
    
    public void IOServer() {
        ServerSocket serverSocket = null;
        InputStream in = null;
        try {
            serverSocket = new ServerSocket(8080);
            int recvMsgSize = 0;
            byte[] recvbuf = new byte[2024];
            while (true) {
                Socket clientSocket = serverSocket.accept();
                SocketAddress clientAddress = clientSocket.getRemoteSocketAddress();
                System.out.println("Handling client at " + clientAddress);
                in = new BufferedInputStream(clientSocket.getInputStream());
                while ((recvMsgSize = in.read(recvbuf)) != -1) {
                    byte[] temp = new byte[recvMsgSize];
                    System.arraycopy(recvbuf, 0, temp, 0, recvMsgSize);
                    System.out.println(new String(temp));
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {}
        }
    }
}
