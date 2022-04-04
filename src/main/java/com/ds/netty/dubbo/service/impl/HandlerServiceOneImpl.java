package com.ds.netty.dubbo.service.impl;

import com.ds.netty.dubbo.service.HandlerServiceOne;
import org.springframework.stereotype.Service;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/18
 * @Description:
 */
public class HandlerServiceOneImpl implements HandlerServiceOne {
    @Override
    public String handleOne(String mes) {
        return"$$$$$$$" + mes + "$$$$$$$$$$$";
    }
}
