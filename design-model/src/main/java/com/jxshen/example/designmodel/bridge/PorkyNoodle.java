package com.jxshen.example.designmodel.bridge;

public class PorkyNoodle extends AbstractNoodle {

    public PorkyNoodle(Peppery style) {
        super(style);
    }

    @Override
    public void eat() {
        System.out.println("Porky noodle is " + super.style.style());
    }

}
