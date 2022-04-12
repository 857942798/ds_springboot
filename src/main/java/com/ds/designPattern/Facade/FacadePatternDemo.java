package com.ds.designPattern.Facade;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/16
 * @Description: 外观设计模式
 */
public class FacadePatternDemo {
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}
