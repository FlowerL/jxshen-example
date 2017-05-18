package com.jxshen.example.nio.sum;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

/**
 * reference from: http://blog.csdn.net/flyingpig4/article/details/5747551
 */
public class SumServer {

    private ByteBuffer _buffer = ByteBuffer.allocate(8);
    private IntBuffer _intBuffer = _buffer.asIntBuffer();
    private SocketChannel _clientChannel = null;
    private ServerSocketChannel _serverChannel = null;
    
    public void start() {
        try {
            openChannel();
            waitForConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void openChannel() throws IOException {
        _serverChannel = ServerSocketChannel.open();
        _serverChannel.socket().bind(new InetSocketAddress(8080));
        _serverChannel.configureBlocking(false);
        System.out.println("Server channel is open");
    }
    
    private void waitForConnection() throws IOException {
        Selector acceptSelector = SelectorProvider.provider().openSelector();
        _serverChannel.register(acceptSelector, SelectionKey.OP_ACCEPT);
        while (acceptSelector.select() > 0) {
            Set<SelectionKey> readyKeys = acceptSelector.selectedKeys();
            Iterator<SelectionKey> i = readyKeys.iterator();
            while(i.hasNext()) {
                SelectionKey sk = i.next();
                i.remove();
                if (sk.isAcceptable()) {
                    ServerSocketChannel nextReady = (ServerSocketChannel)sk.channel();
                    _clientChannel = nextReady.accept();
                    processRequest();
                }
            }
        }
    }
    
    private void processRequest() throws IOException {
        _buffer.clear();
        _clientChannel.read(_buffer);
        int result = _intBuffer.get(0) + _intBuffer.get(1);
        _buffer.flip();
        _buffer.clear();
        _intBuffer.put(0, result);
        _clientChannel.write(_buffer);
    }
    
    public static void main(String[] args) {
        new SumServer().start();
    }
}
