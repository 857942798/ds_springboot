package com.ds.designPattern.abstractFactory;

import com.ds.designPattern.abstractFactory.color.Color;
import com.ds.designPattern.abstractFactory.shape.Shape;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/16
 * @Description:
 */
public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape);
}
