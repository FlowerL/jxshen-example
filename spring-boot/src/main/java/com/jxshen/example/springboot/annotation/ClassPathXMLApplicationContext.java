package com.jxshen.example.springboot.annotation;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * reference from: http://blog.csdn.net/u010987379/article/details/52152795
 */
public class ClassPathXMLApplicationContext {

    private Logger logger = Logger.getLogger(ClassPathXMLApplicationContext.class);
    private List<BeanDefine> beanList = new ArrayList<>();
    private Map<String, Object> singletons = new HashMap<>();

    public ClassPathXMLApplicationContext(String fileName) {
        //读取配置文件中管理的bean
        this.readXML(fileName);
        //实例化bean
        this.instancesBean();
        //注解处理器
        this.annotationInject();
    }

    private void readXML(String fileName) {
        Document document = null;
        SAXReader saxReader = new SAXReader();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            document = saxReader.read(classLoader.getResourceAsStream(fileName));
            Element beans = document.getRootElement();
            for (Iterator<Element> beanIter = beans.elementIterator(); beanIter.hasNext();) {
                Element element = beanIter.next();
                BeanDefine beanDefine = new BeanDefine(element.attributeValue("id"), element.attributeValue("class"));
                beanList.add(beanDefine);
            }
        } catch (DocumentException e) {
            logger.info("读取配置文件出错...");
        }
    }

    private void instancesBean() {
        try {
            for (BeanDefine bean : beanList) {
                singletons.put(bean.getId(), Class.forName(bean.getClassName()).newInstance());
            }
        } catch (Exception e) {
            logger.info("实例化Bean出错...");
        }
    }

    /**
     * 注解处理器
     * 如果注解SjxResource配置了name属性，则根据name所指定的名称获取要注入的实例引用，
     * 如果注解ZSjxResource;没有配置name属性，则根据属性所属类型来扫描配置文件获取要
     * 注入的实例引用
     */
    private void annotationInject() {
        for (Map.Entry<String, Object> entry : singletons.entrySet()) {
            Object bean = entry.getValue();
            if (bean != null) {
                this.propertyAnnotation(bean);
                this.fieldAnnotation(bean);
            }
        }
    }

    /**
     * 处理在set方法加入的注解
     */
    private void propertyAnnotation(Object bean) {
        try {
            PropertyDescriptor[] ps = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
            // 遍历所有属性
            for (PropertyDescriptor pd : ps) {
                // 获取属性对应的setter方法
                Method setter = pd.getWriteMethod();
                if (setter != null && setter.isAnnotationPresent(SjxResource.class)) {
                    // 获取当前注解，并判断name属性是否为空
                    SjxResource resource = setter.getAnnotation(SjxResource.class);
                    String name = "";
                    Object value = null;
                    if (resource.name() != null && !"".equals(resource.name())) {
                        name = resource.name();
                        value = singletons.get(name);
                    }
                    else { // 如果当前注解没有指定name属性，则根据类型匹配
                        for (Map.Entry<String, Object> entry : singletons.entrySet()) {
                            if (pd.getPropertyType().isAssignableFrom(entry.getValue().getClass())) {
                                value = entry.getValue();
                                break;
                            }
                        }

                    }
                    // 允许访问private方法
                    setter.setAccessible(true);
                    // 把引用对象注入属性
                    setter.invoke(bean, value);
                }
            }
        } catch (Exception  e) {
            logger.info("set方法注解解析异常..........");
        }
    }

    /**
     * 处理在字段上的注解
     */
    private void fieldAnnotation(Object bean) {
        try {
            // 获取全部的字段描述
            Field[] fields = bean.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field != null && field.isAnnotationPresent(SjxResource.class)) {
                    SjxResource resource = field.getAnnotation(SjxResource.class);
                    String name = "";
                    Object value = null;
                    if (resource.name() != null && !"".equals(resource.name())) {
                        name = resource.name();
                        value = singletons.get(name);
                    }
                    else {
                        for (Map.Entry<String, Object> entry : singletons.entrySet()) {
                            if (field.getType().isAssignableFrom(entry.getValue().getClass())) {
                                value = entry.getValue();
                                break;
                            }
                        }
                    }
                    field.setAccessible(true);
                    field.set(bean, value);
                }
            }
        } catch (Exception e) {
            logger.info("字段注解解析异常..........");
        }
    }

    public Object getBean(String id) {
        return singletons.get(id);
    }

    public static void main(String[] args) {
        ClassPathXMLApplicationContext ac = new ClassPathXMLApplicationContext("configAnnotation.xml");
        UserServiceImpl userService = (UserServiceImpl) ac.getBean("userService");
        userService.show();
    }
}
