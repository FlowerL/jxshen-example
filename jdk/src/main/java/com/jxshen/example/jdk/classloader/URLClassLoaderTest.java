package com.jxshen.example.jdk.classloader;

import java.net.URL;
import java.net.URLClassLoader;

public class URLClassLoaderTest {
    public static void main(String[] args) {
        URL[] urls = ((URLClassLoader)ClassLoader.getSystemClassLoader()).getURLs();
        for (URL url : urls) {
            System.out.println(url);
        }
    }
}
