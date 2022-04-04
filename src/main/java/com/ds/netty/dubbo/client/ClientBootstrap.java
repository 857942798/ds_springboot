package com.ds.netty.dubbo.client;

import com.ds.netty.dubbo.service.HandlerServiceOne;
import com.ds.netty.dubbo.service.HandlerServiceTwo;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/18
 * @Description:
 */
public class ClientBootstrap {

    public static void main(String[] args) throws Exception {

        //创建代理对象
        HandlerServiceTwo handlerServiceTwo = new ClientFactory<>(HandlerServiceTwo.class).getBean();
        HandlerServiceOne handlerServiceOne = new ClientFactory<>(HandlerServiceOne.class).getBean();

        //通过代理对象调用服务提供者的方法(服务)
        System.out.println(handlerServiceTwo.handleTwo("Hello"));
        System.out.println(handlerServiceOne.handleOne("Hello"));
    }
}
