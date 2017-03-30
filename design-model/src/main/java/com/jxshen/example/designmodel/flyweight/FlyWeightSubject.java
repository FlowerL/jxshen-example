package com.jxshen.example.designmodel.flyweight;

/**
 * FlyWeight model
 * Reference from : http://blog.csdn.net/zhangerqing/article/details/8239539
 */
public class FlyWeightSubject {

    private String pubParam1;
    private String pubParam2;
    private String param3;
    private String param4;
    public String getPubParam1() {
        return pubParam1;
    }
    public void setPubParam1(String pubParam1) {
        this.pubParam1 = pubParam1;
    }
    public String getPubParam2() {
        return pubParam2;
    }
    public void setPubParam2(String pubParam2) {
        this.pubParam2 = pubParam2;
    }
    public String getParam3() {
        return param3;
    }
    public void setParam3(String param3) {
        this.param3 = param3;
    }
    public String getParam4() {
        return param4;
    }
    public void setParam4(String param4) {
        this.param4 = param4;
    }
    
    
}
