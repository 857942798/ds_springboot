package com.ds.test;


/**
 * @author dongsheng
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main4 {
    public static void main(String[] args) {
        ArrayList<Integer> arr=GetLeastNumbers_Solution(new int[]{4,5,1,6,2,7,3,8},4);
        for (int i = 0; i <arr.size() ; i++) {
            System.out.println(arr.get(i));
        }

    }

    public static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        //小顶堆
        PriorityQueue<Integer> p=new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        ArrayList<Integer> arr=new ArrayList<>();
        if(k>input.length){
            return arr;
        }
        for(int i=0;i<input.length;i++){
            p.offer(input[i]);
        }

        for(int j=0;j<k;j++){
            arr.add(p.poll());
        }
        return arr;
    }

}
