package com.jxshen.example.concurrent.gui;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * reference from: java concurrency in practice<br>
 * using IoDH singleton
 */
public class GuiExecutor extends AbstractExecutorService {

    private GuiExecutor() {}
    
    public static final class GuiExecutorHolder {
        private static final GuiExecutor instance = new GuiExecutor(); 
    }
    
    public static GuiExecutor getInstance() {return GuiExecutorHolder.instance;}
    
    public void execute(Runnable r) {
        if (SwingExecutor.isEventDispatchThread()) {
            r.run();
        }
        else {
            SwingExecutor.invokeLater(r);
        }
    }

    @Override
    public void shutdown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Runnable> shutdownNow() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isShutdown() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isTerminated() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        // TODO Auto-generated method stub
        return false;
    }
}
