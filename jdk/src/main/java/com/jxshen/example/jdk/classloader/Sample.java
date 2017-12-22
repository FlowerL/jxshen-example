package com.jxshen.example.jdk.classloader;

public class Sample {

    private Sample instance;

    public void setSample(Object sample) {
        this.instance = (Sample) sample;
    }
}
