package com.jxshen.example.eventdriven.source;

import com.jxshen.example.eventdriven.event.EventInterf;
import com.jxshen.example.eventdriven.listener.EventListenerInterf;

public interface EventSourceInterf {

    void addEventListener(EventListenerInterf<? extends EventInterf> listener);
    
    void removeEventListener(EventListenerInterf<? extends EventInterf> listener);
    
    void notifyEventListener(EventInterf event);
}
