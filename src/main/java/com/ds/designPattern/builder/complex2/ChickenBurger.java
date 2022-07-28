package com.ds.designPattern.builder.complex2;

/**
 * @author: dongsheng
 * @CreateTime: 2022/4/27
 * @Description:
 */
public class ChickenBurger extends Burger {

    @Override
    public float price() {
        return 50.5f;
    }

    @Override
    public String name() {
        return "Chicken Burger";
    }
}
