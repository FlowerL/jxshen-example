package com.jxshen.example.designmodel.future;

public class RealData implements DataInterf {

    protected String data;

    public RealData(String data) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.data = data;
    }

    @Override
    public String getResult() throws InterruptedException {
        return data;
    }
}
