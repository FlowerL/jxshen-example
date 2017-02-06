package com.jxshen.example.eventdriven.test;

import org.junit.Test;

import com.jxshen.example.eventdriven.event.ReadEvent;
import com.jxshen.example.eventdriven.event.WriteEvent;
import com.jxshen.example.eventdriven.listener.WriteEventListener;

public class AbstractEventListenerTest {

    @Test
    public void doTest(){
        WriteEventListener weListener = new WriteEventListener();
        WriteEvent writeEvent = new WriteEvent();
        ReadEvent readEvent = new ReadEvent();
        System.out.println(weListener.isInterested(writeEvent));
        System.out.println(weListener.isInterested(readEvent));
    }
}
