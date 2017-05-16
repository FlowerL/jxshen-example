package com.jxshen.example.concurrent.cas;

public interface PseudoRandom {

    default int calculateNext(int i) {
        return 0;
    }
}
