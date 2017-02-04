package com.jxshen.example.eventdriven.listener;

import com.jxshen.example.eventdriven.event.ReadEvent;

public class ReadEventListener extends AbstractEventListener<ReadEvent> {

    @Override
    public void handleEvent(ReadEvent event) {
        System.out.println("read event come");
    }

}
