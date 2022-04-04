package com.ds.webservice;

import com.ds.webservice.impl.HelloServiceImpl;

import javax.xml.ws.Endpoint;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/15
 * @Description:
 */
public class webServiceServer {
    public static void main(String[] args){
        //发布webservice
        String wsAddress = "http://localhost:6868/01-ws-java-server/ws";
        Endpoint endpoint = Endpoint.publish(wsAddress, new HelloServiceImpl());
        System.out.println("webservice发布成功：" + endpoint.isPublished());
    }
}
