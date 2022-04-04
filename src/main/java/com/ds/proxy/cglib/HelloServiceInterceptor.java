package com.ds.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/18
 * @Description:
 */
public class HelloServiceInterceptor implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class<?> clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object arg0, Method arg1, Object[] arg2,
                            MethodProxy arg3) throws Throwable {
        Object result = arg3.invokeSuper(arg0, arg2);
        return result;
    }

}
