package com.jxshen.example.designmodel.bridge;

/**
 * Bridge model<br>
 * One thing has two direction to change<br>
 * reference from : http://blog.csdn.net/xsl1990/article/details/16359289
 */
public class PlainStyle implements Peppery {

    @Override
    public String style() {
        return "Plain";
    }

}
