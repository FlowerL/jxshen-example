package com.jxshen.example.designmodel.strategy;

/**
 * Strategy model<br>
 * switch among different alogrithm<br>
 * reference from : http://blog.csdn.net/xsl1990/article/details/16359289
 */
public class OldStrategy implements DiscountStrategy {

    @Override
    public Double getDiscount(Double originPrice) {
        System.out.println("Use old discount strategy...");
        return 0.7 * originPrice;
    }

}
