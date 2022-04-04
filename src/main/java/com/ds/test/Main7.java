package com.ds.test;


/**
 * @author dongsheng
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main7 {
    public static void main(String[] args) {
        int n =10;
        Scanner scanner=new Scanner(System.in);
        String str=scanner.next();
        char[] c=str.toCharArray();
        List<String> lis = new ArrayList<String>();
        for(int i = 0; i < c.length;i++){
            lis.add("-1");
        }

        f1(lis,0,c);
        System.out.println(count);

    }
    static int count = 0;
    private static void f1(List<String> lis, int start,char[] c) {

        if (start >= lis.size()) {
            count++;
            System.out.println(lis);
            return;
        }

        for (int i = 0; i < c.length; i++) {
            if (!lis.contains(i)) {
                lis.set(start,String.valueOf(c[i]));
            } else continue;

            f1(lis, start + 1,c);
            lis.set(start, "-1");

        }
    }

}
