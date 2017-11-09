package com.jxshen.example.designmodel.future;

public class FutureData implements DataInterf {
    private RealData realData = null;
    boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    public boolean isReady() {
        return isReady;
    }

    @Override
    public synchronized String getResult() throws InterruptedException {
        while (!isReady) { // buzy-loop to guarantee actual ready
            wait();
        }
        return realData.getResult();
    }
}
