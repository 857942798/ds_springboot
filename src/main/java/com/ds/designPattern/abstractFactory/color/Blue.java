package com.ds.designPattern.abstractFactory.color;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/16
 * @Description:
 */
public class Blue implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }
}
