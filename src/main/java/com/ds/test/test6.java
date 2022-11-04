package com.ds.test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>
 *
 * </p>
 *
 * @author dongsheng
 * @date 2022/8/19
 */
public class test6 {
    public static void main(String[] args){
//        System.out.println("第8天："+getAns(8));
//        System.out.println("第10天："+getAns(10));
//        System.out.println("第12天："+getAns(12));
//        System.out.println("第13天："+getAns(13));
//        System.out.println("第14天："+getAns(14));
//        System.out.println("第15天："+getAns(15));
//        System.out.println("第16天："+getAns(16));
        long s = System.currentTimeMillis();
        System.out.println("第40天："+getAns(40));
        long e = System.currentTimeMillis();
        System.out.println(e-s);
    }

    // 队列
    public static int getAns(int n){
        Deque<Integer> list=new LinkedList<>();
        list.addLast(0);
        for(int i=0;i<n;i++){
            int size = list.size();
            while(size>0){
                Integer date = list.pollFirst();
                ++date;
                if(date<=6){
                    list.addLast(date);
                }else if(date>=7&&date<=13){
                    list.addLast(date);
                    list.addLast(1);
                    list.addLast(1);
                    list.addLast(1);
                }
                size--;
            }
        }
        return list.size();
    }

}
