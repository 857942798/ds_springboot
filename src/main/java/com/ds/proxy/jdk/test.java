package com.ds.proxy.jdk;

import com.ds.proxy.cglib.HelloServiceInterceptor;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/18
 * @Description:
 */
public class test {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        for(int i=0;i<1000;i++){
            // 目标对象
            HelloServiceImpl target = new HelloServiceImpl();
            // 给目标对象，创建代理对象
            HelloService proxy = (HelloService) new JdkProxyFactory(target).getProxyInstance();
            // class $Proxy0   内存中动态生成的代理对象
            // 执行方法   【代理对象】
            proxy.hello("张三");
            proxy.hi("jdk动态代理");
        }
        long end=System.currentTimeMillis();
        System.out.println("jdk="+(end-start));
    }
}
