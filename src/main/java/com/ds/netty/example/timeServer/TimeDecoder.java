package com.ds.netty.example.timeServer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/7
 * @Description:
 */
public class TimeDecoder  extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) { // (2)
        if (in.readableBytes() < 4) {
            return; // (3)
        }
//        out.add(in.readBytes(4)); // (4)
        out.add(new UnixTime(in.readUnsignedInt()));
    }
}
