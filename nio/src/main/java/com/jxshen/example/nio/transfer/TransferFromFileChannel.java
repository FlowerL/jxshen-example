package com.jxshen.example.nio.transfer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class TransferFromFileChannel {

    public void method() {
        RandomAccessFile fromFile = null;
        RandomAccessFile toFile = null;
        try {
            fromFile = new RandomAccessFile("from.txt", "rw");
            toFile = new RandomAccessFile("to.txt", "rw");
            FileChannel fromChannel = fromFile.getChannel();
            FileChannel toChannel = toFile.getChannel();
            
            long position = 0;
            long count = fromChannel.size();
            toChannel.transferFrom(fromChannel, position, count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if (fromFile != null) {
                    fromFile.close();
                }
                if (toFile != null) {
                    toFile.close();
                }
            } catch (IOException e) {
                
            }
        }
    }
}
