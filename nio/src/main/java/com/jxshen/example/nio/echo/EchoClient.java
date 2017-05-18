package com.jxshen.example.nio.echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * send string to server, receive it back and print it
 * @author jxshen
 *
 */
public class EchoClient {

    private ByteBuffer _buffer = ByteBuffer.allocate(256);
    private CharBuffer _charBuffer;
    private SocketChannel _channel;
    
    public EchoClient() {
        _charBuffer = _buffer.asCharBuffer();
    }
    
    public String echo(String str) {
        String result = null;
        try {
            _channel = connect();
            sendStringRequest(str);
            result = receiveResponse();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (_channel != null) {
                try {
                    _channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    
    private SocketChannel connect() throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress("localhost", 10001);
        return SocketChannel.open(socketAddress);
    }
    
    private void sendStringRequest(String str) throws IOException {
        if (str == null) {
            return;
        }
        if (str.getBytes().length > 128) {
            System.err.println("String can't be longer than 128 chars");
            return;
        }
        _buffer.clear();
        _charBuffer.put(str);
        _channel.write(_buffer);
        System.out.println(String.format("Send String %s", str));
    }
    
    private String receiveResponse() throws IOException {
        _buffer.clear();
        _channel.read(_buffer);
        _charBuffer.flip();
        String result = _charBuffer.toString();
        return result;
    }
    
    public static void main(String[] args) {
        EchoClient echoClient = new EchoClient();
        Scanner reader = null;
        try {
            reader = new Scanner(System.in);
            String str = reader.nextLine();
            System.out.println(String.format("The result of echo is %s", echoClient.echo(str)));
        } catch (Exception e) {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
