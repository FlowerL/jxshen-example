package com.jxshen.example.jdk.classloader;

import java.io.*;

/**
 * reference from: http://blog.csdn.net/jearbilove/article/details/25538819
 */
public class FileSystemClassLoader extends ClassLoader {

    private String rootDir;

    public FileSystemClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.equals("com.jxshen.example.jdk.classloader.Sample")) {
            byte[] classData = getClassData(name);
            if (classData == null) {
                throw new ClassNotFoundException();
            }
            else {
                return defineClass(name, classData, 0, classData.length);
            }
        }
        else {
            return super.loadClass(name);
        }
    }

    private byte[] getClassData(String className) {
        String path = classNameToPath(className);
        try {
            InputStream is = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int numsRead = 0;
            while ((numsRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, numsRead);
            }
            is.close();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String classNameToPath(String className) {
        return rootDir + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
    }
}
