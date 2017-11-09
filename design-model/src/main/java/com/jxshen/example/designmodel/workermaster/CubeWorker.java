package com.jxshen.example.designmodel.workermaster;

public class CubeWorker extends AbstractWorker<Integer, Integer> {
    @Override
    protected Integer handle(Integer input) {
        return input * input * input;
    }
}
