package com.jxshen.example.designmodel.bridge;

public class BeefNoodle extends AbstractNoodle {

    public BeefNoodle(Peppery style) {
        super(style);
    }

    @Override
    public void eat() {
        System.out.println("Beef noodle is " + super.style.style());
    }

}
