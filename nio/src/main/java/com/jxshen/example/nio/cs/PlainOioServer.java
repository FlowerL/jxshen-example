package com.jxshen.example.nio.cs;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * reference from: Netty In Action
 */
public class PlainOioServer {
    
    public void server(int port) throws IOException {
        final ServerSocket serverSock = new ServerSocket(port);
        while(true) {
            final Socket clientSock = serverSock.accept();
            System.out.println(String.format("Accepted connection from [%s]", clientSock));
            new Thread(new Runnable(){
                @Override
                public void run() {
                    OutputStream os = null;
                    try {
                        os = clientSock.getOutputStream();
                        os.write("Hello!\r\n".getBytes(Charset.forName("UTF-8")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
