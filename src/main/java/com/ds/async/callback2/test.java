package com.ds.async.callback2;

/**
 * @author: dongsheng
 * @CreateTime: 2022/5/16
 * @Description: 同步回调
 */
public class test {
    public static void main(String[] args){
        Genius genius = new Genius();
        Person you = new Person(genius);
        you.ask();
    }
}
