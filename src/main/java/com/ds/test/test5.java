package com.ds.test;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/17
 * @Description:
 * 已知新冠病毒的潜伏期为6天，即在感染后第7天会发病，
 * 发病状态下的患者每天会新感染3名健康人员，患者在第14天时会自愈，恢复为健康人员（当天不会感染其他人）。
 *
 * 某城市一名人员在接触快递后被感染了新冠病毒，将此记为第1天
 * 假设该城市没有其他新冠疫情输入和输出，求N天结束时该城市的感染总人数。
 *
 * 输入天数N（1<N<=40），请输出当前天数感染新冠的总人数（不含已自愈人员）
 *
 * 7: 4
 * 8: 7
 * 9: 10
 * 10: 13
 * 12: 19
 * 13: 28
 * 14: 45
 * 15: 72
 */
public class test5 {

    // dp[n]=dp[n-1]+3*(6天前被隐形感染的人数 dp[n-6] + 7到13天之间的显性感染的人数)-13天前被隐形感染的人会治愈
    // dp[n] 当前感染的人数，昨天感染人数+今天显性*3（隐性）-今天治愈
    // 今天显性=6天前被隐形感染的人数 dp[n-6] + 7到13天之间的显性感染的人数
    // 隐性的感染人数=今天显性*3
    // 治愈的人数=13天前被隐形感染的人会治愈
    public static void main(String[] args){
//        System.out.println("第1天："+getAns(1));
//        System.out.println("第8天："+getAns(8));
//        System.out.println("第13天："+getAns(13));
//        System.out.println("第16天："+getAns(16));
//        System.out.println("第40天："+getAns(40));
//        System.out.println("第50天："+getAns(50));
        long s = System.currentTimeMillis();
        System.out.println("第60天："+getAns(60));
        long e = System.currentTimeMillis();
        System.out.println(e-s);
    }

    // 队列
    public static int getAns(int n){
        Map<Integer, Integer> map = new TreeMap<>();
        map.put(0,1);
        for(int i=0;i<n;i++){
            Map<Integer, Integer> tmp= new TreeMap<>();
            Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Integer> entry = iterator.next();
                Integer date = entry.getKey();
                date++;
                if(date<=13){
                    tmp.put(date,entry.getValue());
                }
                if(date>=7&&date<=13){
                    tmp.put(1,entry.getValue()*3+(tmp.get(1)==null?0:tmp.get(1)));
                }
                iterator.remove();
            }
            // 合并两个map
            map.putAll(tmp);
        }
        int ans=0;
        for (Map.Entry<Integer, Integer> entry :map.entrySet()){
            ans=ans+entry.getValue();
        }
        return ans;
    }

}
