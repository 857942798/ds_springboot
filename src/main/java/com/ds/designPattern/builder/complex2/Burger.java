package com.ds.designPattern.builder.complex2;

/**
 * @author: dongsheng
 * @CreateTime: 2022/4/27
 * @Description:
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
