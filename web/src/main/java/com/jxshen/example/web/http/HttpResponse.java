package com.jxshen.example.web.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * reference from: http://www.cnblogs.com/bigdataZJ/p/TomcatSourceZJ2.html
 * 
 * a simple http response<br>
 */
public class HttpResponse {

    private OutputStream os;
    private HttpGetRequest req;
    
    public HttpResponse(OutputStream os) {
        this.os = os;
    }

    public HttpGetRequest getReq() {
        return req;
    }

    public void setReq(HttpGetRequest req) {
        this.req = req;
    }
    
    public void sendStaticResource() throws IOException {
        String uri = req.getUri();
        if (HttpServer.STOP_URL.equalsIgnoreCase(uri)) {
            sendStop();
            return;
        }
        
        byte[] buf = new byte[2048];
        FileInputStream fis = null;
        try {
            File file = new File(HttpServer.WEB_ROOT, uri);
            if (file.exists()) {
                fis = new FileInputStream(file);
                int n = fis.read(buf);
                while (n != -1) {
                    os.write(buf);
                    n = fis.read(buf);
                }
            } else {
                sendNotFound();
            }
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }
    
    private void sendStop() throws IOException {
        String stopMsg = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n" 
                + "Content-Length: 21\r\n" + "\r\n"
                + "<h1>Server Close</h1>";
        os.write(stopMsg.getBytes());
    }
    
    private void sendNotFound() throws IOException {
        String errorMessage = "HTTP/1.1 404 File Not Found\r\n"
                + "Content-Type: text/html\r\n" 
                + "Content-Length: 23\r\n" + "\r\n"
                + "<h1>File Not Found</h1>";
        os.write(errorMessage.getBytes());
    }
}
