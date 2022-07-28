package com.ds.udep.config;

import com.ais.udep.client.netty.config.UdepClientConfigurer;
import com.ais.udep.client.netty.resolve.UdepClientMessageResolverFactory;
import com.ds.udep.reslove.ClientMessageResolver;
import com.ds.udep.reslove.WebServiceMessageResolver;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: dongsheng
 * @CreateTime: 2022/4/25
 * @Description:
 */
@Configuration
public class CusClientConfig implements UdepClientConfigurer {

    @Override
    public void registerResolvers(UdepClientMessageResolverFactory udepClientMessageResolverFactory){
        udepClientMessageResolverFactory.registerResolver(new WebServiceMessageResolver());	// 注册客户端webService类型消息处理器
        udepClientMessageResolverFactory.registerResolver(new ClientMessageResolver());	// 注册客户端webService类型消息处理器
    }

    @Override
    public CopyOnWriteArrayList<String> subscribeService(CopyOnWriteArrayList<String> list) throws Exception {
        list.add("echoService");
        return list;
    }

}
