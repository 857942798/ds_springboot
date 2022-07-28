package com.ds.designPattern.builder.complex2;

/**
 * @author: dongsheng
 * @CreateTime: 2022/4/27
 * @Description:
 */
public class Pepsi extends ColdDrink {

    @Override
    public float price() {
        return 35.0f;
    }

    @Override
    public String name() {
        return "Pepsi";
    }
}
