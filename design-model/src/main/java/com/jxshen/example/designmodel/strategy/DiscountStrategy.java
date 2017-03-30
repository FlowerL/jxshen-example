package com.jxshen.example.designmodel.strategy;

/**
 * Strategy model<br>
 * switch among different alogrithm<br>
 * reference from : http://blog.csdn.net/xsl1990/article/details/16359289
 */
public interface DiscountStrategy {

    Double getDiscount(Double originPrice);
}
