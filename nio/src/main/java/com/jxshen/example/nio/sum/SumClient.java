package com.jxshen.example.nio.sum;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.SocketChannel;

/**
 * reference from: http://blog.csdn.net/flyingpig4/article/details/5747551
 */
public class SumClient {

    private ByteBuffer _buffer = ByteBuffer.allocate(8);
    private IntBuffer _intBuffer;
    private SocketChannel _channel;
    
    public SumClient() {
        _intBuffer = _buffer.asIntBuffer();
    }
    
    public int getSum(int first, int second) {
        int result = 0;
        try {
            _channel = connect();
            sendSumRequest(first, second);
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
        InetSocketAddress socketAddress = new InetSocketAddress("localhost", 8080);
        return SocketChannel.open(socketAddress);
    }
    
    private void sendSumRequest(int first, int second) throws IOException {
        _buffer.clear();
        _intBuffer.put(0, first);
        _intBuffer.put(1, second);
        _channel.write(_buffer);
        System.out.println(String.format("Send sum request %d + %d", first, second));
    }
    
    private int receiveResponse() throws IOException {
        _buffer.clear();
        _channel.read(_buffer);
        return _intBuffer.get(0);
    }
    
    public static void main(String[] args) {
        SumClient sumClient = new SumClient();
        System.out.println(String.format("The result of sum is %d", sumClient.getSum(100, 324)));
    }
}
