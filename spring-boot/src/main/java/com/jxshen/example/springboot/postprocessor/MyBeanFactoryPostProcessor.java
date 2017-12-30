package com.jxshen.example.springboot.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("调用MyBeanFactoryPostProcessor的postProcessBeanFactory");
        BeanDefinition bd = beanFactory.getBeanDefinition("configTestBean");
        System.out.println("属性值=================" + bd.getPropertyValues().toString());
        MutablePropertyValues pv = bd.getPropertyValues();
        if (pv.contains("remark")) {
            pv.addPropertyValue("remark", "beanFactoryPostProcessor修改了备注信息");
        }
    }
}
