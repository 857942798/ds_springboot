package com.ds.test;


/**
 * @author dongsheng
 */

import java.util.*;

public class Main5 {
    //十个数选三个数的排列组合
    public static void main(String[] args) {
        String[] strs=new String[]{"20M","300G","1T"};
        TreeMap<Integer,String> map=new TreeMap<>();

        for (int i = 0; i < strs.length; i++) {
             String s=strs[i].substring(0,strs[i].length()-1);
            String danwei=strs[i].substring(strs[i].length()-1);
            int value=Integer.valueOf(s);
            if(danwei.equals("G")){
                value=Integer.valueOf(s)*1000;
            }else if(danwei.equals("T")){
                value=Integer.valueOf(s)*1000*1000;
            }
           map.put(value,strs[i]);
        }
        Iterator<Integer> itr=map.keySet().iterator();
        while (itr.hasNext()){
            Integer key=itr.next();
            System.out.println(map.get(key));
        }
    }

}
