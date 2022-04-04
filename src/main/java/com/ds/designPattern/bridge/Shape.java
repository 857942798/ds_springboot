package com.ds.designPattern.bridge;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/16
 * @Description:
 */
public abstract class Shape {
    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}
