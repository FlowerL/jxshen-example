package com.jxshen.example.springboot.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("configTestBean")) {
            System.out.println("MyBeanPostProcessor postProcessBeforeInitialization");
            try {
                Field field = bean.getClass().getDeclaredField("remark");
                if (field != null) {
                    field.setAccessible(true);
                    field.set(bean, "beanPostProcessor修改了备注信息");
                    System.out.println(bean.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
