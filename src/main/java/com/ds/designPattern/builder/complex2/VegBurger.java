package com.ds.designPattern.builder.complex2;

/**
 * @author: dongsheng
 * @CreateTime: 2022/4/27
 * @Description:
 */
public class VegBurger extends Burger {

    @Override
    public float price() {
        return 25.0f;
    }

    @Override
    public String name() {
        return "Veg Burger";
    }
}
