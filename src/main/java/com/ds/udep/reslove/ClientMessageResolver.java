package com.ds.udep.reslove;

import com.ais.udep.core.message.UdepRequest;
import com.ais.udep.core.message.UdepResponse;
import com.ais.udep.core.message.type.UdepMessageType;
import com.ais.udep.core.netty.resolve.Resolver;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author: dongsheng
 * @CreateTime: 2022/4/18
 * @Description:
 */
public class ClientMessageResolver implements Resolver {

    @Override
    public boolean support(UdepRequest message) {
        try {
            return message.getType()== UdepMessageType.REQUEST.getValue();
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
