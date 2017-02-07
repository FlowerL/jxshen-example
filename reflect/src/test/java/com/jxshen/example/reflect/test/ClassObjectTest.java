package com.jxshen.example.reflect.test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.jxshen.example.reflect.clazz.ClassAnnotation1;
import com.jxshen.example.reflect.clazz.ClassInterface1;
import com.jxshen.example.reflect.clazz.ClassObject;
import com.jxshen.example.reflect.clazz.EnumObject;

public class ClassObjectTest {

    @Test
    public void doTest() throws Exception {
        
        //boolean isInstance(Object obj): 判断obj是否class的类或者子类
        ArrayList<String> arrayList = new ArrayList<String>();
        boolean isInstance = List.class.isInstance(arrayList);
        System.out.println("ArrayList is instance of List : " + isInstance);
        isInstance = Collection.class.isInstance(arrayList);
        System.out.println("ArrayList is instance of Collection : " + isInstance);
        
        //T cast(Object obj):将obj强制转换为Class对象所表示的接口或类
        List<?> list = List.class.cast(arrayList);
        System.out.println("ArrayList cast to List is : " + list.toString());
        Collection<?> coll = Collection.class.cast(arrayList);
        System.out.println("ArrayList cast to Collection is : " + coll.toString());
        
        /** 
         * 获取类上的注解(Annotation)、成员变量(Field)、构造方法(Constructor)、方法(Method) 
         * 1、获取当前类所有的 getDeclaredXX  
         * 2、获取当前类和父类中公有的 getXX 
         */  
        
        //获取类中的方法
        Method[] methods = null;
        
        //获得当前类和父类中的公有方法(不包括编译器生成的synthetic和bridge方法)
        methods = ClassObject.class.getMethods();
        System.out.println("ClassObject get methods are : " + Arrays.toString(methods));
        
        //获得当前类中声明的所有方法(包括私有，不包括编译器生成的synthetic和bridge方法)
        methods = ClassObject.class.getDeclaredMethods();
        System.out.println("ClassObject get declared methods are : " + Arrays.toString(methods));
        
        //获得当前类和父类中修饰为public的内部类，内部接口和内部注解(不包括编译器生成的synthetic类)
        Class<?>[] classes = ClassObject.class.getClasses();
        System.out.println("ClassObject get classes are : " + Arrays.toString(classes));
        
        //获得当前类中声明的所有内部类、内部接口、内部注解(包括私有，不包括编译器生成的synthetic类)
        classes = ClassObject.class.getDeclaredClasses();
        System.out.println("ClassObject get declared classes are : " + Arrays.toString(classes));
        
        //获得该类的加载器类
        ClassLoader classLoader = ClassObject.class.getClassLoader();
        System.out.println("ClassObject get ClassLoader is : " + classLoader);
        System.out.println("ClassObject get ClassLoader's father is : " + classLoader.getParent());
        System.out.println("ClassObject get ClassLoader's grandfather is : " + classLoader.getParent().getParent());
        
        //返回数组中的元素类型
        String[] strArray = new String[2];
        Class<?> componentType = strArray.getClass().getComponentType();
        System.out.println("The component type is : " + componentType);
        
        /**
         * getEnclosingXXX,返回该类在哪里定义
         * Class:在类中定义的内部类或者匿名类
         * Constructor:在构造函数重定义
         * Method:在方法中定义
         */
        Class<?> enclosingClass = ClassObject.Father.class.getEnclosingClass();
        System.out.println("Class father is defined in : " + enclosingClass);
        
        //返回枚举类的元素
        EnumObject[] enumConstants = EnumObject.class.getEnumConstants();
        System.out.println("The enum constants are : " + Arrays.toString(enumConstants));
        
        //返回该类直接实现的所有接口的Class类型(即不带泛型，数组型，通配符或者泛型变量等)，不包括父类接口实现
        Class<?>[] interfaces = ClassObject.Child.class.getInterfaces();
        System.out.println("ClassObject implements interfaces are : " + Arrays.toString(interfaces));
        
        //返回该类直接实现的所有接口的Type类型，不包含父类实现的接口
        Type[] types = ClassObject.Child.class.getGenericInterfaces();
        System.out.println("ClassObject implements types are : " + Arrays.toString(types));
        
        //返回该类的超类的Class类型
        Class<? super ClassObject.Child<?,?>> superClass = ClassObject.Child.class.getSuperclass();
        System.out.println("The superClass of Child is : " + superClass);
        
        //返回该类的超类的Type类型
        Type genericSuperType = ClassObject.Child.class.getGenericSuperclass();
        System.out.println("The genericSuperClass of Child is : " + genericSuperType);
        
        //返回该类或接口的java语言修饰符
        int modifiers = ClassObject.class.getModifiers();
        String modify = Modifier.toString(modifiers);
        System.out.println("The modifiers of ClassObject are : " + modify);
        
        //返回该类所在的包
        Package pkg = ClassObject.Child.class.getPackage();
        System.out.println("The package of Child is : " + pkg);
        
        //加载指定的资源，以“/”开头是绝对路径，绝对于src目录的路径,即寻找/src/config/resource.txt
        InputStream is = ClassObject.class.getResourceAsStream("/config/resource.txt");
        //没有“/”表示相对于当前包的路径,即寻找/src/main/java/com/jxshen/example/reflect/config/resource.txt
        is = ClassObject.class.getResourceAsStream("config/resource.txt");
        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //返回该类上所有泛型
        TypeVariable<Class<ClassObject.Child>>[] typeParameters = ClassObject.Child.class.getTypeParameters();
        System.out.println("The type parameters of Child are : " + Arrays.toString(typeParameters));
        
        //判断是否为注解
        boolean isAnnotation = ClassAnnotation1.class.isAnnotation();
        System.out.println(ClassAnnotation1.class.getSimpleName() + " is annotations is :" + isAnnotation);
        
        //判断是否是匿名类
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                System.out.println("runnable is anomymousClass : " + this.getClass().isAnonymousClass());
            }
        };
        runnable.run();
        
        //判断是否是数组
        System.out.println("String[] is array is : " + String[].class.isArray());
        
        //boolean isAssignableFrom(Class<?> cls): 判断Object的class是不是cls的父类或者父接口,可以是相同的类
        boolean isAssignableFrom = ClassObject.Father.class.isAssignableFrom(ClassObject.Child.class);
        System.out.println("Father is assignableFrom Child is :" + isAssignableFrom);
        
        //判断是否枚举类
        System.out.println("EnumObject is enum is : " + EnumObject.class.isEnum());
        
        //判断是否是接口
        System.out.println("ClassInterface1 is interface is : " + ClassInterface1.class.isInterface());
        
        //判断是否是内部类(成员类)
        System.out.println("Father is memberClass is : " + ClassObject.Father.class.isMemberClass());
        System.out.println("runnable is memberClass is : " + runnable.getClass().isMemberClass());
        
        //判断是否为8个基本数据类型
        System.out.println("int is primitive type is : " + int.class.isPrimitive());
        
        //创建实体
        ClassObject classObject = ClassObject.class.newInstance();
    }
}
