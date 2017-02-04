package com.jxshen.example.eventdriven.source;

import java.util.LinkedList;
import java.util.List;

import com.jxshen.example.eventdriven.event.EventInterf;
import com.jxshen.example.eventdriven.listener.EventListenerInterf;

/**
 * 模拟IO事件源
 * @author jxshen
 *
 */
public class IOSimulation implements EventSourceInterf {
    
    protected List<EventListenerInterf<? extends EventInterf>> listenerList = new LinkedList<EventListenerInterf<? extends EventInterf>>();

    @Override
    public void addEventListener(EventListenerInterf<? extends EventInterf> listener) {
        listenerList.add(listener);
    }

    @Override
    public void removeEventListener(EventListenerInterf<? extends EventInterf> listener) {
        listenerList.remove(listener);
    }

    @Override
    public void notifyEventListener(EventInterf event) {
        for (EventListenerInterf eventListener : listenerList) {
            if (eventListener.isInterested(event)) {
                eventListener.handleEvent(event);
            }
        }
    }

}
