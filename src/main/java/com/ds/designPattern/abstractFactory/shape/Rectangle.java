package com.ds.designPattern.abstractFactory.shape;

import com.ds.designPattern.abstractFactory.shape.Shape;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/16
 * @Description:
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
