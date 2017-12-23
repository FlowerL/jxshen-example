package com.jxshen.example.jdk.classloader.selfdefine1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * reference from: http://blog.csdn.net/chenjiazhan/article/details/37714401
 */
public class CustomClassLoaderTest {

    public static void main(String[] args) {
        String name = "com.jxshen.example.jdk.classloader.selfdefine1.Printer";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        IPrinter printer = null;
        while (true) {
            System.out.println("输入任意字符进行热加载，直接敲回车键退出程序");
            try {
                String line = reader.readLine();
                if (line != null && line.length() > 0) {
                    CustomClassLoader loader = new CustomClassLoader(Thread.currentThread().getContextClassLoader(), name);
                    Class<?> clazz = loader.loadClass();
                    /**
                     * 被子加载器加载的类拥有被父加载器加载的类的可见性
                     * Printer是由自定义类加载器加载的，
                     * 而它的父类IPrinter是由系统类加载器加载的，
                     * 因此IPrinter对于Printer具有可见性，
                     * 因此转型成功，并不会因为类加载器不同导致ClassCastException异常
                     */
                    printer = (IPrinter) clazz.newInstance();
                    printer.print();
                }
                else {
                    break;
                }
            } catch (Exception e) {

            }
        }
    }
}
