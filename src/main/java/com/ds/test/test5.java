package com.ds.test;

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
//        System.out.println("第7天："+getAns(7));
//        System.out.println("第8天："+getAns(8));
//        System.out.println("第10天："+getAns(10));
//        System.out.println("第12天："+getAns(12));
        System.out.println("第13天："+getAns(13));
//        System.out.println("第14天："+getAns(14));
//        System.out.println("第15天："+getAns(15));
    }

    public static int getAns(int n){
        int[] dp = new int[n+1];
        if(n<=6){
            return 1;
        }
        for(int i=0;i<=6;i++){
            dp[i]=1;
        }
        for(int k=7;k<=n;k++){
            if(k-13<=0){
                dp[k]=dp[k-1]+3*dp[k-6];
            }else{
                dp[k]=dp[k-1]+3*dp[k-6]-dp[k-13];
            }
        }
        return dp[n];
    }
}
