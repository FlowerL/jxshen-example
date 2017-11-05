package com.jxshen.example.jdk.system;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * reference from: http://blog.csdn.net/yitongfling/article/details/6270495
 */
public class TestProperties {

    public static void main(String[] args) {
        Properties properties = System.getProperties();
        Enumeration enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            System.out.println(key + "=" + properties.get(key));
        }
        Process p = null;
        try {
            p = Runtime.getRuntime().exec("notepad.exe "
                    + properties.get("user.dir") + properties.get("file.separator")
                    + "TestProperties.java");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        p.destroy();
    }
}
