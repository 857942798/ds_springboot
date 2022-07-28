package com.ds.netty.test.resolver;


import com.ds.netty.test.Message;

/**
 * @author: dongsheng
 * @CreateTime: 2022/4/13
 * @Description: 消息处理器，将根据消息类型选择对应的处理器进行处理
 */
public interface Resolver {
     boolean support(Message message) ;
     Message resolve(Message message) ;
}
