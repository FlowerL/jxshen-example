package com.jxshen.example.jdk.classloader;

import com.sun.deploy.util.StringUtils;

import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * 查看加载的类的路径
 * 这就是StringUtils这个类所在的jar包，一目了然。但是有一个限制经由系统类加载器所加载的类不能使用该方法来获取路径，
 * 假如你对String类做上面的测试就会发生空指针异常，因为所得到的CodeSource为空，你可以在程序启动的时候使用
 * -verbose来查看系统类的装载信息，使用方式有如：
 * java -verbose MyApp
 *
 * @reference from: http://blog.csdn.net/yicong406880638/article/details/48295029
 */
public class CheckClassLoader {

    public static void main(String[] args) {
        ProtectionDomain pd = StringUtils.class.getProtectionDomain();
        CodeSource cs = pd.getCodeSource();
        System.out.println(cs.getLocation());
    }
}
