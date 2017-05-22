package com.jxshen.example.nio.cs;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * reference from: Netty In Action
 */
public class PlainOioServer {
    
    public void server(int port) throws IOException {
        final ServerSocket serverSock = new ServerSocket(port);
        while(true) {
            final Socket clientSock = serverSock.accept();
        }
    }
}
