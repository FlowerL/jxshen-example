package com.jxshen.example.designmodel.chain;

public abstract class AbstractHandler {

    private HandlerInterf handler;

    public HandlerInterf getHandler() {
        return handler;
    }

    public void setHandler(HandlerInterf handler) {
        this.handler = handler;
    }
    
    
}
