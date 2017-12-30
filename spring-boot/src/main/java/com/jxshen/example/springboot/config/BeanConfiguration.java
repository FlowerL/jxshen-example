package com.jxshen.example.springboot.config;

import com.jxshen.example.springboot.bean.ConfigTestBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ConfigTestBean configTestBean() {
        ConfigTestBean configTestBean = new ConfigTestBean();
        configTestBean.setDesc("测试一下啦");
        configTestBean.setRemark("这是备注信息啦啦啦");
        return configTestBean;
    }
}
