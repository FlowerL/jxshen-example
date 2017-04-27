package com.jxshen.example.concurrent.block.noninterrupt;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Reference from : java concurrency in practice<br>
 * response to interruption when blocked in socket i/o
 */
public class ReadThread extends Thread {

    private final Socket socket;
    private final InputStream in;
    private static final int BUFSZ = 1024;
    public ReadThread(Socket socket) throws IOException {
        super();
        this.socket = socket;
        this.in= socket.getInputStream();
    }
    
    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            super.interrupt();
        }
    }
    @Override
    public void run() {
        try {
            byte[] buf = new byte[BUFSZ];
            while (true) {
                int count = in.read(buf);
                if (count < 0) {
                    break;
                } else if (count > 0) {
                    processBuffer(buf, count);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private void processBuffer(byte[] buf, int count) {}
}
