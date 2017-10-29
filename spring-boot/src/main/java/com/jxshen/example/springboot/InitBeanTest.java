package com.jxshen.example.springboot;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class InitBeanTest implements InitializingBean,ApplicationListener<ContextRefreshedEvent> {

    @Resource
    DemoService demoService;

    public InitBeanTest() {
        System.err.println("----> InitSequenceBean: constructor: "+demoService);
    }

    @PostConstruct
    public void postConstruct() {
        System.err.println("----> InitSequenceBean: postConstruct: "+demoService);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.err.println("----> InitSequenceBean: afterPropertiesSet: "+demoService);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        System.err.println("----> InitSequenceBean: onApplicationEvent");
    }
}
