package com.ds.designPattern.bridge;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/16
 * @Description:
 */
public class Circle extends Shape {

    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void draw() {
        drawAPI.drawCircle(radius,x,y);
    }

}
