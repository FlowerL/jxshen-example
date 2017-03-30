package com.jxshen.example.designmodel.bridge;

/**
 * Bridge model<br>
 * One thing has two direction to change<br>
 * reference from : http://blog.csdn.net/xsl1990/article/details/16359289
 */
public abstract class AbstractNoodle {

    protected Peppery style;
    
    public AbstractNoodle(Peppery style) {
        this.style = style;
    }
    
    public abstract void eat();
}
