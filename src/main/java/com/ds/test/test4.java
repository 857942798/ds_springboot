package com.ds.test;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/17
 * @Description:
 *
 * 已知一个公司在进行数据传输前会先对数据进行压缩，数据由 a-z，A-Z 组成，
 * 压缩规则为：
 * 出现连续多个重复的字符，调整为字符+数字的方式。
 * 出现连续多组重复的字符串，调整为
 *
 * 如   aabcccdddd 会被压缩为 a2b1c3d4
 *      abcabcbbbd 会被压缩为  [abc]2b3d
 *      abcabcaabcabcadddd 会被压缩为  [[abc]2a1]2d4
 *
 * 如果压缩后的字符长度大于原字符串，则进行还原
 *
 * 如 abbcd  压缩为 a1b2c1d1，长度大于原字符串，则压缩结果为 abbcd
 *
 * 输入原始字符串（长度不超过500），返回压缩后的字符串
 *
 */
public class test4 {
    public static void main(String[] args){
        String str="aabcccdddd";
        char[] source=str.toCharArray();
        // 压缩后的字符最大不会超过原字符的2倍
        String ans="";
        System.out.println(yasuo(ans,source,0,1));
    }

    public static String yasuo(String ans,char[] source, int l,int r){
        // 如果答案已经比原本的长则直接返回原数组
        if(ans.length()>source.length){
            return String.valueOf(source);
        }
        // 1。字符串相同直接合并
        while(source[l]==source[r]&&r<source.length){
            r++;
        }
        ans=ans+source[l]+(r-l);
        l=r;
        ans=yasuo(ans,source,l,r);
        // 2。字符串不同，使用滑动窗口找出不重复子川
        return ans;
    }
}
