package com.ds.netty.dubbo.service.impl;

import com.ds.netty.dubbo.service.HandlerServiceTwo;
import org.springframework.stereotype.Service;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/18
 * @Description:
 */
public class HandlerServiceTwoImpl implements HandlerServiceTwo {

    @Override
    public String handleTwo(String mes) {
        return "*****" + mes + "*****";
    }
}
