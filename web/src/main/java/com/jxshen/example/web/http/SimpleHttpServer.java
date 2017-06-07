package com.jxshen.example.web.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A simple http server using bio to read a socket stream<br> 
 * and response the uri in http request line to client, <br>
 * that is uri after method blank and before the blank of protocol/version<br>
 * if the uri is "/stop", server close<br>
 * just test a method to read a complete arrival socket stream with a very small buffer<br>
 * so the buffer should be used repeatedly to join the hold arrival message<br>
 * the key point is BufferedRead.ready() function which tell the next read() is guaranteed not to block for input<br>
 * but the ready() return false do not guarantee the next read() is 100% block<br>
 * 
 * @author jxshen
 *
 */
public class SimpleHttpServer {
    
    public static final int SMALL_BUF_SIZE = 8;
    public static final int PORT = 8980;
    public static final int BACK_LOG = 50;
    // client can use http get uri to close server, eg: http://localhost:8080/stop
    private static final String STOP_URL = "/stop";
    // if client stop server, the string of response
    private static final String CLOSE_RESP_STR = "Server Close";
    private static volatile boolean stop = false; 
    
    // The html template of response
    private static final String HTML = "HTTP/1.1 200 OK\r\n"
            + "Content-Type: text/html\r\n" 
            + "Content-Length: %d\r\n" + "\r\n"
            + "%s";
    
    public static void main(String[] args) {
        new SimpleHttpServer().run();
    }

    public void run() {
        ServerSocket server = null;
        try {
            server = new ServerSocket();
            server.bind(new InetSocketAddress(PORT), BACK_LOG);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Socket client = null;
        InputStream is = null;
        OutputStream os = null;
        while (!stop) {
            try {
                client = server.accept();
                is = client.getInputStream();
                os = client.getOutputStream();
                
                // handle inputStream
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder reqStr = new StringBuilder();
                char[] buf = new char[SMALL_BUF_SIZE];
                do {
                    if (br.read(buf) != -1) {
                        reqStr.append(buf);
                    }
                } // the key point to read a complete arrival socket stream with bio but without block
                while (br.ready()); 
                
                // get uri in http request line
                String respStr = parse(reqStr.toString());
                
                // handle outputStream
                if (stop = STOP_URL.equalsIgnoreCase(respStr)) {
                    respStr = CLOSE_RESP_STR;
                    System.out.println("client require server to stop");
                }
                
                // join the html content
                respStr = "<h1>" + respStr + "</h1>";
                os.write(String.format(HTML, respStr.length(), respStr).getBytes());
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            server.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * get uri in http request line to client, that is uri after the blank of method and before the blank of protocol/version<br>
     * eg: for a http get request, the request line maybe: GET /user?name=jxshen HTTP/1.1<br>
     * then the function return "user"<br>
     * 
     */
    public static String parse(String source) {
        if(source == null || source.length() == 0) {
            return new String();
        }
        
        int startIndex;
        startIndex = source.indexOf(' ');
        if (startIndex != -1) {
          int paramIndex = source.indexOf('?', startIndex + 1);
          int secondBlankIndex = source.indexOf(' ', startIndex + 1);
          int endIndex = -1;
          if (secondBlankIndex > paramIndex) {
              endIndex = secondBlankIndex;
          } else {
              endIndex = paramIndex;
          }
          if (endIndex > startIndex)
            return source.substring(startIndex + 1, endIndex);
        }
        return new String();
    }
}
