package com.ds.test;


/**
 * @author dongsheng
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 *
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
 * 示例：
 *      输入：nums = [1,2,3,4]
 *      输出：[1,3,2,4]
 *      注：[3,1,2,4] 也是正确的答案之一。
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main8 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        // 首位指针，原地置换
        // 如果头指针是奇数，start++
        // 如果尾指针是奇数，和头指针交换，如果是偶数，则end--
        int start=0;
        int end=nums.length-1;
        while (start!=end){
            // 找到第一个偶数
            if((nums[start]&1)==1){
                start++;
                continue;
            }
            // 找到最后一个奇数
            if((nums[end]&1)==0){
                end--;
                continue;
            }
            int tem=nums[start];
            nums[start]=nums[end];
            nums[end]=tem;
        }
        for (int num : nums) {
            System.out.println(num);
        }


    }

}
