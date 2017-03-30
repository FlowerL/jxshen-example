package com.jxshen.example.designmodel;

import org.junit.Test;

import com.jxshen.example.designmodel.strategy.DiscountContext;
import com.jxshen.example.designmodel.strategy.VipStrategy;

public class StrategyTest {

    @Test
    public void doTest() {
        DiscountContext dc = new DiscountContext(null);
        Double price = new Double(79);
        //使用默认的打折策略
        System.out.println("79元的书默认打折后的价格是："
            + dc.getDiscountPrice(price));
        dc.setDiscountStrategy(new VipStrategy());
        //使用VIP打折得到打折价格
        System.out.println("89元的书对VIP用户的价格是："
            + dc.getDiscountPrice(price));
    }
}
