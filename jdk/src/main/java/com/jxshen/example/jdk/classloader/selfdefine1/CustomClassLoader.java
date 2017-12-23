package com.jxshen.example.jdk.classloader.selfdefine1;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * reference from: http://blog.csdn.net/chenjiazhan/article/details/37714401
 */
public class CustomClassLoader extends ClassLoader {

    private String name;

    public CustomClassLoader(ClassLoader parent, String name) {
        super(parent);
        if (name == null || name.isEmpty()) {
            throw new NullPointerException();
        }
        this.name = name;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> clazz = null;
        /**如果是我们想要热加载的类则调用我们重写的findClass方法来加载**/
        if (name.equals(this.name) && !name.startsWith("java")) {
            /**先看看要热加载的类之前是否已经加载过了，因为一个类加载器只能加载一个类一次，加载多次会报异常**/
            clazz = findLoadedClass(name);
            if (clazz == null) {
                clazz = findClass(name);
            }

            /**
             * 类的生命周期包括：加载、验证、准备、解析、初始化、使用、卸载。其中验证、准备、解析统称为连接
             * 如果要连接类
             */
            if (resolve) {
                resolveClass(clazz);
            }
            return clazz;
        }
        return super.loadClass(name, resolve);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = c2f(name);
        byte[] bytes = f2b(fileName);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private String c2f(String name) {
        /**编译后的class文件存放的目录**/
        String baseDir = "D:\\git\\jxshen-example\\jdk\\target\\classes\\";
        name = name.replace(".", File.separator);
        name = baseDir + name + ".class";
        return name;
    }

    private byte[] f2b(String fileName) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[4096];
        int numsRead = -1;
        try (InputStream is = new BufferedInputStream(new FileInputStream(fileName))) {
            while ((numsRead = (is.read(bytes))) != -1) {
                baos.write(bytes, 0, numsRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    private byte[] f2bNio(String fileName) {
        byte[] bytes = null;
        try (RandomAccessFile file = new RandomAccessFile(fileName, "r");
             FileChannel fileChannel = file.getChannel()) {
             ByteBuffer buffer = ByteBuffer.allocate(1024);
             bytes = new byte[(int)fileChannel.size()];
             int index = 0;
             while (fileChannel.read(buffer) > 0) {
                 buffer.flip();
                 while (buffer.hasRemaining()) {
                     buffer.flip();
                     bytes[index++] = buffer.get();
                 }
                 buffer.clear();
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  bytes;
    }

    /** 热加载类 **/
    public Class<?> loadClass() {
        try {
            return loadClass(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
