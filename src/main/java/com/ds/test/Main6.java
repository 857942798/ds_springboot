package com.ds.test;


/**
 * @author dongsheng
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;

public class Main6 {
    //十个数选三个数的排列组合
    public static void main(String[] args) {
        int[] num=new int[]{1,2,3,4,5};

    }
    public ArrayList<String> Permutation(String str) {
        //回溯法
        ArrayList<String> arr=new ArrayList<>();
        huisu(str.toCharArray(), 0,arr);
        Collections.sort(arr);
        return arr;
    }
    //c数组，i，当前的位置，已拼接的字符串
    public void huisu(char[] c,int i,ArrayList<String> arr){
        if(i==c.length-1){
            String str=String.valueOf(c);
            if(!arr.contains(str)){
                arr.add(str);
            }
        }else{
            for(int j=i;j<c.length;j++){
                swap(c,i,j);
                huisu(c,i+1,arr);
                swap(c,i,j);
            }
        }

    }
    public void swap(char[] c,int i,int j){
        char temp=c[j];
        c[j]=c[i];
        c[i]=temp;
    }
}
