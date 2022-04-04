package com.ds.designPattern.abstractFactory;

import com.ds.designPattern.abstractFactory.color.Color;
import com.ds.designPattern.abstractFactory.shape.Circle;
import com.ds.designPattern.abstractFactory.shape.Rectangle;
import com.ds.designPattern.abstractFactory.shape.Shape;
import com.ds.designPattern.abstractFactory.shape.Square;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/16
 * @Description:
 */
public class ShapeFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }

    @Override
    public Color getColor(String color) {
        return null;
    }
}
