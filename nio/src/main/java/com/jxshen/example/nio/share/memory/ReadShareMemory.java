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
public class ReadShareMemory {
    
    private static final int SIZE = 1024;

    public static void main(String[] args) throws Exception {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile("D:/swap.mm", "rw");
            FileChannel fc = raf.getChannel();
            MappedByteBuffer mbb = fc.map(MapMode.READ_WRITE, 0, SIZE);
            int lastIndex = 0;
            for (int i = 1; i < 27; i++) {
                int flag = mbb.get(0); // read-write indicator
                int index = mbb.get(1); // position indicator
                if (flag != 2 || index == lastIndex) { // if not readable or not new data, replicate
                    i--;
                    continue;
                }
                lastIndex = index;
                System.out.println(String.format("Program ReadShareMemory: [%d] Index : [%d] Read Data : [%c] ",
                        System.currentTimeMillis(), index, (char)mbb.get(index)));
                mbb.put(0, (byte)0); // Readable
                if (index == 27) {
                    break;
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (raf != null) {
                raf.close();
            }
        }
        
    }
}
