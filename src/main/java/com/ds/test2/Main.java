package com.ds.test2;


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author dongsheng
 */
public class Main {
   private static Set<String> set = new HashSet<>();
    //归宿法求全排列
    public static void main(String[] args){

       int[] num={1,0,0,0,0,1,0,0};
        num=getStr(num);
        for (int i = 0; i < num.length; i++) {
            System.out.print(num[i]);
        }

    }

    public static int[] getStr(int[] num){
        int[] arr=new int[num.length];
        for (int i=0;i<num.length;i++){
            int j=0;
            if(i==0){
                j=num[i+1]+0;
                if(j==0||j==2){
                    arr[i]=0;
                }else{
                    arr[i]=1;
                }
            }else if(i==num.length-1){
                j=num[i-1]+0;
                if(j==0||j==2){
                    arr[i]=0;
                }else{
                    arr[i]=1;
                }
            }else{
                j=num[i-1]+num[i+1];
                if(j==0||j==2){
                    arr[i]=0;
                }else{
                    arr[i]=1;
                }
            }
        }
        num=arr;
        return num;
    }


}
