package com.ds.designPattern.builder.complex;

/**
 * @author: dongsheng
 * @CreateTime: 2022/4/12
 * @Description:
 * 测试类
 */
public class Test {
    public static void main(String[] args) {
        TerrainBuilder builder = new ComplexTerrainBuilder();
        Terrain t = builder.buildFort()
                .buildMine()
                .buildWall()
                .build();
        System.out.println(t);
    }
}

