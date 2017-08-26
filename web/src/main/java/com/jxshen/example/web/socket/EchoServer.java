package com.jxshen.example.web.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {

    private static final int threadPoolSize;
    private static final ExecutorService threadPool;
    
    static {
        threadPoolSize = Runtime.getRuntime().availableProcessors() * 2;
        threadPool = Executors.newFixedThreadPool(threadPoolSize);
    }
    
    public static void main(String[] args) throws UnknownHostException {
        ServerSocket serverSock = null;
        try {
            serverSock = new ServerSocket();
            serverSock.bind(new InetSocketAddress("localhost", 8080));
            while (true) {
                final Socket sock = serverSock.accept();
                threadPool.execute(new Runnable() {
                    public void run() {
                        InputStream is = null;
                        OutputStream os = null;
                        int n = 0;
                        try {
                            is = sock.getInputStream();
                            os = sock.getOutputStream();
                            byte[] buf = new byte[1024];
                            while ((n = is.read(buf)) != -1) {
                                System.out.println(String.format("Thread[%s] get msg: [%s]", 
                                        Thread.currentThread().getName(), new String(buf, 0, n)));
                                os.write(buf, 0, n);
                                os.flush();
                            }
                        } catch (IOException e) {
                            try {
                                is.close();
                                os.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
            }
        } catch (IOException e) {
            try {
                serverSock.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
