package com.jxshen.example.designmodel.strategy;

/**
 * Strategy model<br>
 * switch among different alogrithm<br>
 * reference from : http://blog.csdn.net/xsl1990/article/details/16359289
 */
public class DiscountContext {

    private DiscountStrategy discountStrategy;

    public DiscountContext() {
        super();
    }

    public DiscountContext(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public DiscountStrategy getDiscountStrategy() {
        return discountStrategy;
    }

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }
    
    public Double getDiscountPrice(Double price) {
        if (discountStrategy == null) {
            discountStrategy = new OldStrategy();
        }
        return this.discountStrategy.getDiscount(price);
    }
}
