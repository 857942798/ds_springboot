package com.ds.designPattern.decorator;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/17
 * @Description:
 */
public abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    public void draw(){
        decoratedShape.draw();
    }
}
