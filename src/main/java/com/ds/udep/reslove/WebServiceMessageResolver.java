package com.ds.udep.reslove;

import com.ais.udep.core.message.UdepRequest;
import com.ais.udep.core.message.UdepResponse;
import com.ais.udep.core.netty.resolve.Resolver;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author: dongsheng
 * @CreateTime: 2022/4/18
 * @Description:
 */
public class WebServiceMessageResolver implements Resolver {

    @Override
    public boolean support(UdepRequest message) {
        try {
            JSONObject data = (JSONObject) message.getData();
            return "echoService".equals(data.getString("serverName"));
        }catch (Exception e){
            // 如果报错返回false
            return false;
        }
    }

    @Override
    public UdepResponse resolve(ChannelHandlerContext ctx, UdepRequest request) {
        return UdepResponse.success(null);
    }
}
