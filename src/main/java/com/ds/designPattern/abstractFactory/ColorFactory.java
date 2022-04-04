package com.ds.designPattern.abstractFactory;

import com.ds.designPattern.abstractFactory.color.Blue;
import com.ds.designPattern.abstractFactory.color.Color;
import com.ds.designPattern.abstractFactory.color.Green;
import com.ds.designPattern.abstractFactory.color.Red;
import com.ds.designPattern.abstractFactory.shape.Circle;
import com.ds.designPattern.abstractFactory.shape.Rectangle;
import com.ds.designPattern.abstractFactory.shape.Shape;
import com.ds.designPattern.abstractFactory.shape.Square;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/16
 * @Description:
 */
public class ColorFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType){
        return null;
    }

    @Override
    public Color getColor(String color) {
        if(color == null){
            return null;
        }
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        } else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        return null;
    }
}
