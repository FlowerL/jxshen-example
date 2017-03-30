package com.jxshen.example.designmodel.command;

public class Captain {

    private CmdInterf command;

    public CmdInterf getCommand() {
        return command;
    }

    public void setCommand(CmdInterf command) {
        this.command = command;
    }

    public Captain(CmdInterf command) {
        super();
        this.command = command;
    }
    
    public void cmd() {
        command.exec();
    }
}
