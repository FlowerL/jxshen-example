package com.jxshen.example.reflect.clazz;

@ClassAnnotation1
@ClassAnnotation2
public class ClassObject {
    
    public int publicMember;
    private int privateMember;
    public static int publicStaticMember;
    private static int privateStaticMember;
    public final int publicFinalMember = 9;
    private final int privateFinalMember=10;
    public final static int publicFinalStaticMember = 1;
    private final static int privateFinalStaticMember=0;
    
    /**
     * 公有函数
     * @param param
     */
    public void publicMethod(String param){}
    
    /**
     * 私有静态函数
     * @param param
     */
    private void privteMethod(String param){}
    
    /**
     * 公有静态函数
     * @param param
     */
    public static void publicStaticMethod(String param){}
    
    /**
     * 私有静态函数
     * @param param
     */
    private static void privateStaticMethod(String param){}
    
    public static class Father<T> implements ClassInterface1{
        public class FatherInnerClass{} //father内部类(非静态，会有指向父类的指针)
    }
    
    public static class Child<T,E> extends Father<T> implements ClassInterface2<T>, ClassInterface3 {
        public class ChildInnerClass{} //Child内部类(非静态，会有指向父类的指针)
    }
    
}
