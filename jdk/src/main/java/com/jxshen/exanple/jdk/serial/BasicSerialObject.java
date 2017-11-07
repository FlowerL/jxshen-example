package com.jxshen.exanple.jdk.serial;

import lombok.Data;

import java.io.Serializable;
@Data
public class BasicSerialObject implements Serializable {

    private String value;

    public static void main(String[] args) {
        BasicSerialObject object = new BasicSerialObject();
        object.setValue("name");
        ObjectStreamUtil.writeObj2File("file1", object);
        System.out.println(ObjectStreamUtil.readObjfromFile("file1"));
    }
}
