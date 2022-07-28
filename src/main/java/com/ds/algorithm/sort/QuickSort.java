package com.ds.algorithm.sort;

/**
 * @author: dongsheng
 * @CreateTime: 2022/5/18
 * @Description:  快速排序
 *
 * 1、队列第一个元素作为基准值
 * 2、当左指针小于右指针时
 *      从右往左扫描，找到第一个比基准值小的元素
 *      从左往右扫描，找到第一个比基准值大的元素
 *      头尾指针对应的数字交换
 * 3、左指针对应项于基准值交换
 */
public class QuickSort {
    public static void main(String[] args){
        int[] arr = {5, 1, 7, 3, 1, 6, 9, 4};
        qickSort1(arr,0, arr.length-1);
        for (int i = 0; i <= arr.length-1; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    static void qickSort1(int[] arr,int leftIndex,int rigthIndex){
        if(leftIndex >=rigthIndex){
            return;
        }
        int left=leftIndex;
        int right=rigthIndex;
        int key=arr[left];
        while (left<right) {
            while(left<right&&arr[right]>=key){
                right--;
            }
            arr[left]=arr[right];
            while (left<right&&arr[left]<=key){
                left++;
            }
            arr[right]=arr[left];
        }
        arr[left]=key;
        qickSort1(arr,leftIndex,left-1);
        qickSort1(arr,right+1,rigthIndex);
    }

    static void qickSort2(int[] arr,int leftIndex,int rigthIndex){
        int v = arr[leftIndex];
        int i = leftIndex, j = rigthIndex + 1;
        while (true) {
            while (++i <= rigthIndex && arr[i] < v);
            while (--j >= leftIndex && arr[j] > v);
            if (i >= j) {
                break;
            }
            int t = arr[j];
            arr[j] = arr[i];
            arr[i] = t;
        }
        arr[leftIndex] = arr[j];
        arr[j] = v;
    }



    }
