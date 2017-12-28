package com.jxshen.example.springboot.annotation;

public class UserDaoImpl implements UserDao {

    String name;

    @Override
    public void show() {
        System.out.println("这里是方法" + getClass().getSimpleName());
    }
}
