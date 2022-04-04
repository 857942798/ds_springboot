package com.ds.webservice;

import com.ds.webservice.client.HelloServiceImpl;
import com.ds.webservice.client.HelloServiceImplService;

import javax.xml.ws.Endpoint;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/15
 * @Description:
 */
public class webServiceClient {
    public static void main(String[] args){
        //1.创建一个webservice的客户端
        HelloServiceImplService userServiceImplService = new HelloServiceImplService();

        //2.获取远程服务接口对象
        HelloServiceImpl helloService = userServiceImplService.getHelloServiceImplPort();

        //3.直接调用远程服务接口对象的方法
        String hi= helloService.hello("j8ei");
        System.out.println(hi);
        //3.直接调用远程服务接口对象的方法
        String send= helloService.send("j8ei");
        System.out.println(send);
    }
}
