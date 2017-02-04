package com.jxshen.example.eventdriven.listener;

import com.jxshen.example.eventdriven.event.WriteEvent;

public class WriteEventListener extends AbstractEventListener<WriteEvent> {

    @Override
    public void handleEvent(WriteEvent event) {
        System.out.println("write event come");
    }

}
