package com.ds.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *● 小明在玩翻牌游戏，桌子上有一堆写着 1-100之间的整数的卡片。
 * ● 开始时所有卡片正面朝上
 * ●
 * ● 并有9张规则牌，数字为2-10，每张规则牌最多可使用一次
 * ●
 * ● 每次小明可以选择一张卡片k翻转
 * ● 并从规则牌中指定一张m或不指定规则，
 * ●
 * ● 指定规则后，所有与选择卡片数字差值是m倍数的卡片。
 * ● 即 k-m,k-2m,k-3m...k+m,k+2m,k+3m...会被同时翻转
 * ●
 * ● 如果不指定规则则仅翻转卡片k。
 * ●
 * ● 请求出小明将所有卡片翻转成背面的最小翻牌次数。
 * </p>
 *
 * @author dongsheng
 * @date 2022/8/19
 */
public class test8 {
    public static void main(String[] args) {
        int[] numbers = new int[]{6, 9, 12, 15, 18, 32, 56, 64};
        int[] rules = new int[]{2, 3, 8, 9, 10};
        int ans = getAns(numbers, rules);
    }

    public static int getAns(int[] numbers, int[] rules) {
        // 1、将每个数归类到能整除的规则牌中
        Map<Integer, Set<Integer>> cate = new HashMap<>();
        for (int number : numbers) {
            for (int rule : rules) {
                if (number / rule == 0) {
                    if (cate.containsKey(rule)) {

                    }
                }
            }
            // 2、有交集的规则牌不能同时使用
            // 3、没有交集的规则牌组数量+剩余不能整除任何规则牌的卡牌数量就是最小值
        }
        return 1;
    }
}
