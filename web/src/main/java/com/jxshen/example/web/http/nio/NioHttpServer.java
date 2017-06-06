package com.jxshen.example.web.http.nio;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import com.jxshen.example.web.http.HttpServer;

/**
 * A simple Nio Http Server just send back static resources
 * @author jxshen
 *
 */
public class NioHttpServer {

    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
    public static final String STOP_URL = "/stop";
    
    private final int PORT;
    private final int BACK_LOG;
    
    private volatile boolean stop = false;
    
    public NioHttpServer(int port, int back_log) {
        this.PORT = port;
        this.BACK_LOG = back_log;
    }
    
    public static void main(String[] args) {
        new HttpServer(8080, 100).run();
    }
    
    public void run() {
        Selector selector = null;
        ServerSocketChannel server = null;
        try {
            selector = Selector.open();
            server = ServerSocketChannel.open();
            server.bind(new InetSocketAddress(PORT), BACK_LOG);
            server.configureBlocking(false);
            server.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        while (!stop) {
            try {
                if (selector.select() == 0) {
                    continue;
                }
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    iter.remove();
                    if (key.isAcceptable()) {
                        ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                        SocketChannel sc = ssc.accept();
                        sc.configureBlocking(false);
                        sc.register(selector, SelectionKey.OP_READ);
                    }
                    if (key.isReadable()) {
                        SocketChannel sc = (SocketChannel)key.channel();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
        
        try {
            selector.close();
            server.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
