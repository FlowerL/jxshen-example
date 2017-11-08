package com.jxshen.example.jdk.serial;

import lombok.Data;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

@Data
public class ExternSerialObject implements Externalizable {
    private String value;

    public static void main(String[] args) {
        ExternSerialObject object = new ExternSerialObject();
        object.setValue("name");
        ObjectStreamUtil.writeObj2File("file1", object);
        System.out.println(ObjectStreamUtil.readObjfromFile("file1"));
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("writeExternal before:" + value);
        value = "writeExternal";
        out.writeObject(value);
        System.out.println("writeExternal after:" + value);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        value = (String)in.readObject();
        System.out.println("read External before:" + value);
        value = "readExternal";
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        System.out.println("writeObject before:" + value);
        value = "writeObject";
        out.defaultWriteObject();
        System.out.println("writeObject after:" + value);

    }
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        System.out.println("readObject before:" + value);
        value = "readObject";
    }
}
