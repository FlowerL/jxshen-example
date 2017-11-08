package com.jxshen.example.jdk.serial;

import lombok.Data;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

@Data
public class CustomSerialObject implements Serializable {
    private String value;

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        value = "custom";
        Date date = new Date();
        out.writeObject(date);
        out.defaultWriteObject();

    }
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        Date date = (Date)in.readObject();
        System.out.println("read first Date = " + date.toString());
        in.defaultReadObject();
        System.out.println("read value = " + value + " before custom");
        value = "name";
    }

    public static void main(String[] args) {
        CustomSerialObject object = new CustomSerialObject();
        object.setValue("name");
        ObjectStreamUtil.writeObj2File("file1", object);
        System.out.println(ObjectStreamUtil.readObjfromFile("file1"));
    }

}
