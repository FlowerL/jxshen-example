package com.jxshen.example.designmodel;

import org.junit.Test;

import com.jxshen.example.designmodel.command.Command;
import com.jxshen.example.designmodel.command.ProcessArray;

public class CommandTest {

    @Test
    public void doTest() {
        ProcessArray pa = new ProcessArray();
        int[] target = {3, -4, 6, 4};
        pa.each(target, new Command() {

            @Override
            public void process(int[] args) {
                for (int tmp : args) {
                    System.out.println("迭代输出目标数组的元素:" + tmp);
                }
            }
            
        });
        System.out.println("------------------");
        pa.each(target, new Command() {

            @Override
            public void process(int[] args) {
                int sum = 0;
                for (int tmp : args) {
                    sum += tmp;  
                }
                System.out.println("数组元素的总和是:" + sum);
            }
            
        });
    }
}
