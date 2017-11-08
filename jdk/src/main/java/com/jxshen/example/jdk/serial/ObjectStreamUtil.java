package com.jxshen.example.jdk.serial;

import java.io.*;

public class ObjectStreamUtil {

    public static void writeObj2File(String filename, Object object) {
        try {
            FileOutputStream fos = new FileOutputStream(filename, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readObjfromFile(String filename) {
        Object object = null;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            object =  ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return object;
        }
    }
}
