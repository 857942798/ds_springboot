package com.ds.test;


/**
 * @author dongsheng
 */
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x;
        List arr = new ArrayList<>();
        for(int i = 0; i < n; i++){
            x = sc.nextInt();
            arr.add(x);
        }
        System.out.println(getNum(arr));
    }

    public static  int getNum(List arr){
        //找所有可能的路径，最短路径元素和应为list.size-1
        int start=(int) arr.get(0);
        List l=new ArrayList();
        getStr(arr,0,start, arr.get(0).toString(),l);
        return (int) Collections.min(l);
    }


    public  static void getStr(List arr,int xb,int bc,String s,List l){
        for (int i = 1; i <=bc; i++) {
                if(xb>=arr.size()-1){
                    System.out.println(s);
                    int objs=s.split("-").length-1;
                    l.add(objs);
                    return;
                }else{
                    String str=s+"-"+arr.get(xb+i);
                    getStr(arr,xb+i, (int) arr.get(xb+i),str,l);
                }
        }
    }

}
