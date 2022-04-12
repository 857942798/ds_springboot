package com.ds.designPattern.builder.complex;

/**
 * @author: dongsheng
 * @CreateTime: 2022/4/12
 * @Description:
 * 构建器接口
 */
public interface TerrainBuilder {
    TerrainBuilder buildWall();
    TerrainBuilder buildFort();
    TerrainBuilder buildMine();
    Terrain build();
}

