package com.jxshen.example.designmodel.strategy;

/**
 * Strategy model<br>
 * switch among different alogrithm<br>
 * reference from : http://blog.csdn.net/xsl1990/article/details/16359289
 */
public class VipStrategy implements DiscountStrategy {

    @Override
    public Double getDiscount(Double originPrice) {
        System.out.println("Use vip discount strategy...");
        return 0.5 * originPrice;
    }

}
