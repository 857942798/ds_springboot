package com.ds.designPattern.decorator;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/17
 * @Description:
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}
