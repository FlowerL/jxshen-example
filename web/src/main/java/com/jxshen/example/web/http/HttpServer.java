package com.jxshen.example.web.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * reference from: http://www.cnblogs.com/bigdataZJ/p/TomcatSourceZJ2.html
 * 
 * A simple HttpServer just send back static resource <br>
 */
public class HttpServer {
    
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
    public static final String STOP_URL = "/stop";
    
    private final int PORT;
    private final int BACK_LOG;
    
    private volatile boolean stop = false;
    
    public HttpServer(int port, int back_log) {
        this.PORT = port;
        this.BACK_LOG = back_log;
    }
    
    public static void main(String[] args) {
        new HttpServer(8080, 100).run();
    }
    
    public void run() {
        ServerSocket serverSock = null;
        try {
            serverSock = new ServerSocket();
            serverSock.bind(new InetSocketAddress(PORT), BACK_LOG);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        Socket sock = null;
        InputStream is = null;
        OutputStream os = null;
        while (!stop) {
            try {
                sock = serverSock.accept();
                is = sock.getInputStream();
                os = sock.getOutputStream();
                
                HttpGetRequest req = new HttpGetRequest(is);
                req.parse();
                
                HttpResponse resp = new HttpResponse(os);
                resp.setReq(req);
                resp.sendStaticResource();
                
                sock.close();
                // check stop command
                stop = req.getUri().equalsIgnoreCase(STOP_URL);
                if (stop == true) {
                    System.out.println("client require server to stop");
                }
                
            } catch (IOException e) {
                e.printStackTrace();
                if (sock != null) {
                    try {
                        sock.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                continue;
            }
        }
        
        try {
            serverSock.close();
            sock.close();
            is.close();
            os.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
