package com.jxshen.example.designmodel.future;

public class FutureClient {

    public DataInterf requestDate(final String str) {
        final FutureData futureData = new FutureData();

        new Thread(() -> {
            RealData realData = new RealData(str);
            futureData.setRealData(realData);
        }).start();

        return futureData; // return futureData immediately
    }

    public static void main(String[] args) throws InterruptedException {
        FutureClient client = new FutureClient();
        DataInterf data = client.requestDate("name");
//        Thread.sleep(2000);
        System.out.println(((FutureData)data).isReady);
        System.out.println(data.getResult());
        System.out.println(((FutureData)data).isReady);
    }
}
