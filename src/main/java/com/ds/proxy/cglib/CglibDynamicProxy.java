package com.ds.proxy.cglib;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/18
 * @Description:
 */
public class CglibDynamicProxy {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        for(int i=0;i<1000;i++){
            HelloService proxy = (HelloService)(new HelloServiceInterceptor().getProxy(HelloService.class));
            proxy.hello("张三");
            proxy.hi("cglib动态代理");
        }
        long end=System.currentTimeMillis();
        System.out.println("cglib="+(end-start));
    }
}
