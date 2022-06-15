package com.ds.test;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/11
 * @Description:
 *
 *  输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 *  输入：1->2->4, 1->3->4
 *  输出：1->1->2->3->4->4
 */
public class test {

    public static void main(String[] args){
        System.out.println(-128&1);
    }

    public int missingNumber(int[] nums) {
        int length=nums.length;
        if(length==1){
            return nums[0];
        }
        int i=0;
        while(i<=length-2){
            int j=i+1;
            if(nums[j]!=nums[i]+1){
                return nums[i]+1;
            }
            i++;
        }
        return -1;
    }
}
