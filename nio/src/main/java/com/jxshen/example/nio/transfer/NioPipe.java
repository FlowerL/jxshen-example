package com.jxshen.example.nio.transfer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe.SourceChannel;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * http://www.importnew.com/19816.html
 */
public class NioPipe {

    public static void method() {
        Pipe pipe = null;
        ExecutorService exec = Executors.newFixedThreadPool(2);
        try {
            pipe = Pipe.open();
            final Pipe pipeTmp = pipe;
            
            exec.submit(new Callable<Object>(){
                @Override
                public Object call() throws InterruptedException, IOException {
                    SinkChannel sinkChannel = pipeTmp.sink();
                    while (true) {
                        TimeUnit.SECONDS.sleep(1);
                        String newData = "Pipe Test At Time " + System.currentTimeMillis();
                        ByteBuffer buf = ByteBuffer.allocate(1024);
                        buf.clear();
                        buf.put(newData.getBytes());
                        buf.flip();
                        
                        while (buf.hasRemaining()) {
                            System.out.println(buf);
                            sinkChannel.write(buf);
                        }
                    }
                }
            });
            
            exec.submit(new Callable<Object>() {
                @Override
                public Object call() throws InterruptedException, IOException {
                    SourceChannel sourceChannel = pipeTmp.source();
                    while (true) {
                        TimeUnit.SECONDS.sleep(1);
                        ByteBuffer buf = ByteBuffer.allocate(1024);
                        buf.clear();
                        int bytesRead = sourceChannel.read(buf);
                        System.out.println("bytesRead=" + bytesRead);
                        while (bytesRead > 0) {
                            buf.flip();
                            byte b[] = new byte[bytesRead];
                            int i = 0;
                            while (buf.hasRemaining()) {
                                b[i] = buf.get(i);
                                System.out.printf("%X", b[i]);
                                i++;
                            }
                            String s = new String(b);
                            System.out.println("=================||"+s);
                            bytesRead = sourceChannel.read(buf);
                        } 
                    }
                }
            });
        } catch (IOException e) {
            // TODO Auto-generated catch block
        } finally {
            exec.shutdown();
        }
    }
}
