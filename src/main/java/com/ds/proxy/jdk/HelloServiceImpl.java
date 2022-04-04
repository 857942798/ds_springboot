package com.ds.proxy.jdk;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/18
 * @Description:
 */
public class HelloServiceImpl implements  HelloService{
    @Override
    public String hello(String name) {
        return "hello," + name;
    }

    @Override
    public String hi(String msg) {
        return "hi,"+msg;
    }
}
