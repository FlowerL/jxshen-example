package com.jxshen.example.designmodel.chain;

public class MyHandler extends AbstractHandler implements HandlerInterf {

    private String handlerName;
    
    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public MyHandler(String handlerName) {
        super();
        this.handlerName = handlerName;
    }

    @Override
    public void operate() {
        System.out.println(String.format("chain arrives at + %s", handlerName));
        if (super.getHandler() != null) {
            super.getHandler().operate();
        }
    }

}
