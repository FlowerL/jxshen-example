package com.jxshen.example.tomcat.http.server.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer1 {

    // Shutdown command
    private static final String SHUTDOWN_COMMAND = "/shutdown";
    // Shutdown command received
    private boolean shutdown = false;

    public void await() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("localhost"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while(!shutdown) {
            InputStream is = null;
            OutputStream os = null;
            try (Socket socket = serverSocket.accept()) {
                is = socket.getInputStream();
                os = socket.getOutputStream();

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
