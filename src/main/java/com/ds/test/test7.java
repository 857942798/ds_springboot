package com.ds.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author dongsheng
 * @date 2022/8/19
 */
public class test7 {
    public static void main(String[] args){
//        // 商品
//        int[] goods={200,500,400,200,600,300,100};
//        // 优惠卷
//        int[][] coupons={{7,4},{5,3},{2,1},{3,2},{3,1},{4,2},{4,3}};

//        int[] goods={100,200,300,400,500,1000,1500,2000,2500};
//        int[][] coupons={{3,2},{2,0}};

        int[] goods=new int[]{200,500,400,200,600,300,100,500,400,200,600,300,100,900,1000};
        int[][] coupons=new int[][]{{7,4},{5,3},{2,1},{3,2},{3,1},{4,3},{8,6},{5,2},{6,3},{4,1}};
        int ans = getAns(goods, coupons);
        System.out.println(ans);
    }

    public static int getAns(int[] goods,int[][] coupons){
        // 商品goods从小到大排序
        Arrays.sort(goods);
        //对优惠卷先做一次处理，当m>=剩余商品总数时置为-1当成丢弃,m+n>剩余商品总数时,需要剪切
        for(int i=0;i<coupons.length;i++){
            int[] coupon = coupons[i];
            int m=coupon[0];
            int n=coupon[1];
            if(m>=goods.length){
                coupons[i][1]=-1;
                continue;
            }
            while((m+n)>goods.length){
                n--;
            }
            coupons[i][1]=n;
        }

        // 优惠卷优先级排序
        /**
         * 1. 当m+n <= 剩余商品总数,且n尽可能大,m尽可能小
         * 2. m相同时,n越大 优先级越高
         * 3. n相同时,m越小 优先级越高
         * 4. 在使用完一张优惠卷后,再查找下一张可用优惠卷
         */
        Arrays.sort(coupons, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]==o2[1]){
                    return o1[0]-o2[0];
                }
                return o2[1]-o1[1];
            }
        });
        // 答案
        int ans=0;
        // 标记指针，表示选中的商品位置
        int pre=goods.length-1;
        // 记录已买过的商品
        Set<Integer> having=new HashSet<>();
        // 查找可用优惠卷
        for(int i=0;i<coupons.length;i++){
            int[] coupon = coupons[i];
            int m=coupon[0];
            int n=coupon[1];
            // 如果n被标记为-1，代码已经使用过
            if(n==-1){
                continue;
            }
            // 商品已买完，直接推出
            if(pre<0){
                break;
            }
            // 先计算无法使用优惠卷的商品
            for(int j=0;j<(m-n);j++){
                ans=ans+goods[pre];
                having.add(pre);
                pre--;
                // 商品已买完，推出当前循环
                if(pre<0){
                    break;
                }
            }
            // 为了使优惠卷的价值更高,最佳的方式是前一个商品作为优惠卷用以抵消后一个价格高的商品
            while(n>0&&pre>=0){
                // 前一个商品作为优惠卷必须买
                // 后一个商品减去前一个商品得到的差值为实际付出的金额
                int back= goods[pre];
                having.add(pre);
                int front=0;
                if(--pre>=0){
                    front= goods[pre];
                    having.add(pre);
                }
                int diff=back-front;
                ans=ans+diff+front;
                n--;
                pre--;
            }
        }
        // 优惠卷数量太少，导致还有一些商品没买
        for (int k=0;k<goods.length;k++){
            if(having.contains(k)){
                break;
            }
            ans=ans+goods[k];
        }
        return ans;
    }

}
