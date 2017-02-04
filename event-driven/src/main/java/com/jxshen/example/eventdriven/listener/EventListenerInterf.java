package com.jxshen.example.eventdriven.listener;

import com.jxshen.example.eventdriven.event.EventInterf;

public interface EventListenerInterf<T extends EventInterf> {
    
    /**
     * 判断事件是否是该监听器感兴趣的类型
     * @param event
     * @return
     */
    boolean isInterested(EventInterf event);
    
    /**
     * 处理事件
     * @param event
     */
    void handleEvent(T event);
}
