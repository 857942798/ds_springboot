package com.ds.test;


/**
 * @author dongsheng
 */

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        JumpFloor(n,new StringBuilder());
        List a=new ArrayList();
    }

    public static String JumpFloor(int target,StringBuilder str){
        if(target<=0){
            str.append("0");
            return "1";
        }
        if(target==1){
            str.append("1");
            System.out.println(str.toString());
            return "1";
        }else if(target==2){
            str.append("2");
            System.out.println(str.toString());
            return "2";
        } else{
            StringBuilder s=new StringBuilder();
            s.append(JumpFloor(target-1,s)).append(JumpFloor(target-2,s));

            return  s.toString();
        }
    }



}
