package com.jxshen.exanple.jdk.serial;

/**
 * 没有实现Serilizable的Object的序列化测试
 */
public class NoSerialObject {
    private String value;

    public static void main(String[] args) {
        NoSerialObject object = new NoSerialObject();
        object.setValue("name");
        ObjectStreamUtil.writeObj2File("file1", object);
        System.out.println(ObjectStreamUtil.readObjfromFile("file1"));
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "NoSerialObject{" +
                "value='" + value + '\'' +
                '}';
    }
}
