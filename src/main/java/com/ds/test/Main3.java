package com.ds.test;


/**
 * @author dongsheng
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
    }

    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        ArrayList<Integer> arr=new ArrayList<>();
        if(size==0||size>num.length){
            return arr;
        }
        for(int i=0;i<num.length-size;i++){
            int max=num[i];
            for(int j=i+1;j<i+size;j++) {
                if (num[j] > max) {
                    max = num[j];
                }
            }
            arr.add(max);
        }
        return arr;
    }


}
