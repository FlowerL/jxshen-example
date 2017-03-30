package com.jxshen.example.designmodel.state;

/**
 * State model<br>
 * Reference from : http://blog.csdn.net/zhangerqing/article/details/8245537
 */
public class State {

    private String value;

    public State(String value) {
        super();
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public void method1() {
        System.out.println("method1");
    }
    
    public void method2() {
        System.out.println("method2");
    }
}
