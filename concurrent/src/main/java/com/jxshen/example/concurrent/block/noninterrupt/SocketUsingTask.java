package com.jxshen.example.concurrent.block.noninterrupt;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public abstract class SocketUsingTask<T> implements CancellableTask<T> {

    private Socket socket;

    protected synchronized Socket getSocket() {
        return socket;
    }

    protected synchronized void setSocket(Socket socket) {
        this.socket = socket;
    }
    
    public synchronized void cancel() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
            @SuppressWarnings("finally")
            public boolean cancel(boolean mayInterruptIfRunning) {
                try {
                    SocketUsingTask.this.cancel();
                } finally {
                    return super.cancel(mayInterruptIfRunning);
                }
            }
        };
    }
}
