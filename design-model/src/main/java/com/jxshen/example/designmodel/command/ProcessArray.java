package com.jxshen.example.designmodel.command;

/**
 * command model<br>
 * reference from : http://blog.csdn.net/xsl1990/article/details/16359289
 */
public class ProcessArray {

    public void each(int[] target, Command cmd) {
        cmd.process(target);
    }
}
