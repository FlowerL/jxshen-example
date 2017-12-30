package com.jxshen.example.springboot;


import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 这样配置可以同时用内置tomcat启动和war部署启动
 * @author jxshen
 *
 */
@SpringBootApplication
@EnableAsync
@EnableAspectJAutoProxy
//@EnableTransactionManagement
@ComponentScan(basePackages="com.jxshen.example.springboot")
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer implements ApplicationContextAware {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
        System.out.println(ac.getBean("&myFactoryBean").getClass());
    }

    private static ApplicationContext ac;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
    }
}
