package com.ds.designPattern.bridge;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/16
 * @Description:
 */
public class GreenCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}
