package com.ds.proxy.cglib;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/18
 * @Description:
 */
public class HelloService {
    public String hello(String name) {
        return "hello," + name;
    }

    public String hi(String msg) {
        return "hi,"+msg;
    }
}
