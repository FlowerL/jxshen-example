package com.jxshen.example.springboot.annotation;

public class User2DaoImpl implements UserDao {

    String name;

    @Override
    public void show() {
        System.out.println("这里是方法" + getClass().getSimpleName());
    }
}
