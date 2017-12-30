package com.jxshen.example.springboot.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyFactoryBean implements FactoryBean<GetBean> {

    private static GetBean instance = new GetBean();

    @Override
    public GetBean getObject() throws Exception {
        return instance;
    }

    @Override
    public Class<?> getObjectType() {
        return GetBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
