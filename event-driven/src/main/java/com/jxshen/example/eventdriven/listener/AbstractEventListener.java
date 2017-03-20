package com.jxshen.example.eventdriven.listener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.jxshen.example.eventdriven.event.EventInterf;

public abstract class AbstractEventListener<T extends EventInterf> implements EventListenerInterf<T> {

    /**
     * 判断事件是否是该监听器感兴趣的类型
     * @param event
     * @return
     */
    @Override
    public boolean isInterested(EventInterf event) {
        Type listenerSuperType = this.getClass().getGenericSuperclass();
        if (listenerSuperType instanceof ParameterizedType) {
            ParameterizedType listenerParameterizedType = (ParameterizedType)listenerSuperType;
            Class<?> clazz = (Class<?>)listenerParameterizedType.getActualTypeArguments()[0];
            return clazz.equals(event.getClass());
        } 
        else {
            Class<?> clazz = (Class<?>)listenerSuperType;
            return clazz.equals(event.getClass());
        }
    }

}
