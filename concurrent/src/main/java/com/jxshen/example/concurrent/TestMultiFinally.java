package com.jxshen.example.concurrent;

public class TestMultiFinally {

    public static void main(String[] args) {
        try{
            try{
                return;
            } finally {
                System.out.println("我是内层finally");
            }
            
        } finally {
            System.out.println("我是外层finally");
        }
    }
}
