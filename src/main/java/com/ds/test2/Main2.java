package com.ds.test2;


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author dongsheng
 */
public class Main2 {
    private static Set<String> set = new HashSet<>();
    //归宿法求全排列
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String str=scanner.next();
        char[] c=str.toCharArray();
        getStr(c,0);
        System.out.println(set.size());
    }

    public static void getStr(char[] c,int start){
          if(start==c.length-1){
              set.add(String.valueOf(c));
                return;
          }else{
              for (int i = start; i < c.length; i++) {
                  //先交换
                  swap(c,i,start);
                  //递归调用
                  getStr(c,start+1);
                  //再交换回来
                  swap(c,start,i);
              }
          }
    }
    public static void swap(char[] c,int i,int j){
        char temp=c[i];
        c[i]=c[j];
        c[j]=temp;
    }

}
