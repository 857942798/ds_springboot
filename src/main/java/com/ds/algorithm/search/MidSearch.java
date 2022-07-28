package com.ds.algorithm.search;

import java.util.Arrays;

/**
 * @author: dongsheng
 * @CreateTime: 2022/5/18
 * @Description:  二分查找
 *
 *  取数组中间的数 与目标数比较
 *  比目标数大，则从左半部分查找
 *  比目标数小，则从右半部分查找
 *  递归，直到找到目标数
 *
 *
 */
public class MidSearch {
    public static void main(String[] args){
        int[] arr = {1,1,2,3,4,4,5,6};
        int ans=midSearch(arr,0, arr.length-1,5);
        System.out.print(ans+" ");
    }

    static int midSearch(int[] arr,int leftIndex,int rigthIndex,int target) {
        if(leftIndex>rigthIndex){
            return -1;
        }
        int mid=(rigthIndex+leftIndex)/2;
        if(arr[mid]==target){
            return mid;
        }else if(arr[mid]>target){
            return midSearch(arr,leftIndex,mid-1,target);
        }else {
            return midSearch(arr,mid+1,rigthIndex,target);
        }
    }
}
