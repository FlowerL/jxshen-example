package com.jxshen.example.common;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {

    public static <T> void copyProperties(T from, T to) throws Exception {
        if (from == null || to == null) {
            return;
        }
        
        Method[] methods = from.getClass().getDeclaredMethods();
        Map<String, Method> getterMap = new HashMap<String, Method>();
        Map<String, Method> setterMap = new HashMap<String, Method>();
        for (Method method : methods) {
            if (Modifier.isPublic(method.getModifiers())) {
                if (method.getName().startsWith("get")) {
                    getterMap.put(method.getName().substring(3), method);
                }
                if (method.getName().startsWith("is")) {
                    getterMap.put(method.getName().substring(2), method);
                }
                if (method.getName().startsWith("set")) {
                    setterMap.put(method.getName().substring(3), method);
                }
            }
        }
        
        for (Map.Entry<String, Method> entry : setterMap.entrySet()) {
            String propertyName = entry.getKey();
            Method getterMethod = getterMap.get(propertyName);
            Method setterMethod = entry.getValue();
            setterMethod.invoke(to, getterMethod.invoke(from, new Object[]{}));
        }
    }
    
    public static void main(String[] args) throws Exception {
        PropertyObject from = new PropertyObject();
        from.setName("test");
        from.setAge(19);
        from.setMan(false);
        PropertyObject to = new PropertyObject();
        copyProperties(from, to);
        System.out.println(to);
    }
    
    private static class PropertyObject {
        private String name;
        private Integer age;
        private boolean isMan;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Integer getAge() {
            return age;
        }
        public void setAge(Integer age) {
            this.age = age;
        }
        public boolean isMan() {
            return isMan;
        }
        public void setMan(boolean isMan) {
            this.isMan = isMan;
        }
        @Override
        public String toString() {
            return "PropertyObject [name=" + name + ", age=" + age + ", isMan=" + isMan + "]";
        }
    }
}
