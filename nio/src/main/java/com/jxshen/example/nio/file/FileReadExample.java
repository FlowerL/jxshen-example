package com.jxshen.example.nio.file;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileReadExample {

    private static final String FILE_NAME = "D:/example.txt";
    
    public void transational() {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(FILE_NAME));
            byte[] buf = new byte[1024];
            int bytesRead = in.read(buf);
            while (bytesRead!= -1) {
                for (int i = 0; i < bytesRead; i++) {
                    System.out.println((char)buf[i]);
                }
                bytesRead = in.read(buf);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void nio() {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(FILE_NAME,"rw");
            FileChannel fc = raf.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int bytesRead = fc.read(buf);
            while (bytesRead != -1) {
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.println((char)buf.get());
                }
                buf.compact();
                bytesRead = fc.read(buf);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                }
            }
        }
    }
}
