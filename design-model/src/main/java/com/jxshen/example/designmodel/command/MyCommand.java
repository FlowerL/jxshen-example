package com.jxshen.example.designmodel.command;

public class MyCommand implements CmdInterf {

    private Actioner actioner;
    
    public Actioner getActioner() {
        return actioner;
    }

    public void setActioner(Actioner actioner) {
        this.actioner = actioner;
    }

    @Override
    public void exec() {
        actioner.action();
    }

}
