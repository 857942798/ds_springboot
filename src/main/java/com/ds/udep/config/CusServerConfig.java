package com.ds.udep.config;

import com.ais.udep.server.netty.config.UdepServerConfigurer;
import com.ds.udep.service.EchoWebService;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author: dongsheng
 * @CreateTime: 2022/4/25
 * @Description:
 */
@Configuration
public class CusServerConfig implements UdepServerConfigurer {

    @Override
    public Map<String, Object> publishWebService(Map<String, Object> urlMap) {
        // 发布webService服务,url和服务类进行绑定，支持多个url绑定同一个服务类
        urlMap.put("/echoService",new EchoWebService());
        return urlMap;
    }
}
