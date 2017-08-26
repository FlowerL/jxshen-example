package com.jxshen.example.web.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EchoClient {

    public static void main(String[] args) throws IOException {
        while (true) {
            Socket sock = new Socket();
            try {
                Thread.sleep(1000);
                sock.connect(new InetSocketAddress("localhost", 8080));
                InputStream is = sock.getInputStream();
                OutputStream os = sock.getOutputStream();
                os.write("client send msg".getBytes());
                os.flush();
                int n = 0;
                byte[] buf = new byte[1024];
                n = is.read(buf);
                System.out.println(String.format("The server echo [%s]", new String(buf, 0, n)));

            } catch (IOException | InterruptedException e) {
            } finally {
                sock.close();
            }
        }
    }
}
