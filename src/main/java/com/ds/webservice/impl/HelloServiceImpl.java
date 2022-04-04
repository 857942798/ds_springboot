package com.ds.webservice.impl;

import com.ds.webservice.HelloService;

import javax.jws.WebService;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/15
 * @Description:
 */
@WebService
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello from webservice";
    }

    @Override
    public String send(String msg) {
        return "send from webservice";
    }
}
