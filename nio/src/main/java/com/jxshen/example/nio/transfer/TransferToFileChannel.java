package com.jxshen.example.nio.transfer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class TransferToFileChannel {

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
            fromChannel.transferTo(position, count, toChannel);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
