package com.jxshen.example.nio.share.memory;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * reference from: http://blog.csdn.net/flyingpig4/article/details/5747551<br>
 * the first byte of file indicate read-write<br>
 * 0-writable, 1-writing, 3-readable<br>
 * the second byte of file indicate the position
 */
public class WriteShareMemory {
    
    private static final int SIZE = 1024;

    public static void main(String[] args) throws Exception {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile("D:/swap.mm", "rw");
            FileChannel fc = raf.getChannel();
            MappedByteBuffer mbb = fc.map(MapMode.READ_WRITE, 0, SIZE);
            
            // clear file
            for (int i = 0; i < SIZE; i ++) {
                mbb.put(i, (byte)0);
            }
            // from the third byte of file, write A-Z sequently, the second byte indicate current operate position
            for (int i = 65; i < 91; i++) {
                int index = i - 63;
                int flag = mbb.get(0);
                if (flag != 0 ) { // not writable, replicate
                    i--;
                    continue;
                }
                mbb.put(0, (byte)1); // 1 indicate writing
                mbb.put(1, (byte)index); // second byte indicates position
                System.out.println(String.format("Program WriteShareMemory: [%d] : Index : [%d] Write data : [%c]",
                        System.currentTimeMillis(), index, (char)i));
                mbb.put(index, (byte)i);
                mbb.put(0, (byte)2); //readable
                Thread.sleep(513);
            }
        } catch (Exception e) {
            throw e;
        } 
        finally{
            if (raf != null) {
                raf.close();
            }
        }
    }
}
