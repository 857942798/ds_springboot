package com.ds.designPattern.adapter;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/16
 * @Description:
 */
public class Mp4Player implements AdvancedMediaPlayer{

    @Override
    public void playVlc(String fileName) {
        //什么也不做
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: "+ fileName);
    }
}