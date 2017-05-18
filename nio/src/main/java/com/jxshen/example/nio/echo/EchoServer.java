package com.jxshen.example.nio.echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

/**
 * receive string from client and send it back to client
 * @author jxshen
 *
 */
public class EchoServer {

    private ByteBuffer _buffer = ByteBuffer.allocate(256);
    private CharBuffer _charBuffer = _buffer.asCharBuffer();
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
        _serverChannel.socket().bind(new InetSocketAddress(10001));
        _serverChannel.configureBlocking(false);
        System.out.println("Server is open");
    }
    
    private void waitForConnection() throws IOException {
        Selector acceptSelector = SelectorProvider.provider().openSelector();
        _serverChannel.register(acceptSelector, SelectionKey.OP_ACCEPT);
        while (acceptSelector.select() > 0) {
            Set<SelectionKey> readyKeys = acceptSelector.selectedKeys();
            Iterator<SelectionKey> i = readyKeys.iterator();
            while (i.hasNext()) {
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
        _charBuffer.flip();
        String result = _charBuffer.toString();
        _buffer.clear();
        _charBuffer.put(result);
        _clientChannel.write(_buffer);
    }
    
    public static void main(String[] args) {
        new EchoServer().start();
    }
}
