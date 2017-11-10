package com.jxshen.example.designmodel.workermaster;

public class CubeWorker extends AbstractWorker<Integer, Integer> {
    @Override
    public Integer handle(Integer input) {
        return input * input * input;
    }
}
