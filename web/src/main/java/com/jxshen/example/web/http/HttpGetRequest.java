package com.jxshen.example.web.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * reference from: http://www.cnblogs.com/bigdataZJ/p/TomcatSourceZJ2.html
 * 
 * a simple http get request<br>
 */
public class HttpGetRequest {
    
    private InputStream is;
    private String uri;

    public HttpGetRequest(InputStream is) {
        this.is = is;
    }
    
    /**
     * parse the uri in 1st line of http get request<br>
     * just get the url without param<br>
     * use BufferedReader.ready() to decide if a socket stream at end(not reliable)
     */
    public void parse() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder requestString = new StringBuilder();
        char[] buf = new char[8];
        do {
            if (br.read(buf) != -1) {
                requestString.append(buf);
            }
            
        } while (br.ready());
        uri = getUriWithoutParam(requestString.toString());
    }
    
    /**
     * get the 1st blank index after [GET or POST]<br>
     * if exist param in uri, ex: GET uri?key=value, get the ? index<br>
     * otherwise, get the 2nd blank index after [GET or POST]<br>
     * then get the url without param between the 1st index and the 2nd or ? index<br>
     */
    private String getUriWithoutParam(String requestString) {
        int startIndex;
        startIndex = requestString.indexOf(' ');
        if (startIndex != -1) {
          int paramIndex = requestString.indexOf('?', startIndex + 1);
          int secondBlankIndex = requestString.indexOf(' ', startIndex + 1);
          int endIndex = -1;
          if (secondBlankIndex > paramIndex) {
              endIndex = secondBlankIndex;
          } else {
              endIndex = paramIndex;
          }
          if (endIndex > startIndex)
            return requestString.substring(startIndex + 1, endIndex);
        }
        return null;
    }
    
    public String getUri() {
        return uri;
    }
    
}
