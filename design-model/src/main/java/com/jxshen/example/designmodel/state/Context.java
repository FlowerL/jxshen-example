package com.jxshen.example.designmodel.state;

/**
 * State model<br>
 * Reference from : http://blog.csdn.net/zhangerqing/article/details/8245537
 */
public class Context {

    private State state;

    public Context(State state) {
        super();
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    
    public void method() {
        if ("state1".equals(state.getValue())) {
            state.method1();
        }
        else if ("state2".equals(state.getValue())) {
            state.method2();
        }
    }
}
