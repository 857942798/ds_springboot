package com.ds.netty.example.timeServer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/7
 * @Description:
 *
 * 一32位整数的数据量非常小，在本例中不应用被分割。然而，问题在于他确实有可能被分割，可能性随着通信数据量的增大而增大。
 * 一个简单的方法是创建一个内部的cumulative buffer(累积缓冲区)，等待数据直到接收到4个字节为止
 *
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    private ByteBuf buf;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        buf = ctx.alloc().buffer(4); // (1)
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        buf.release(); // (1)
        buf = null;
    }

    /**
     * handler 检查缓冲区buf是否接收到足够的数据 (4个字节)，若是，则进行实际业务处理。
     * 否则当有更多数据到达时，netty 会再次调用channelRead()，直到缓冲区累积到4个字节。
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        ByteBuf m = (ByteBuf) msg;
//        buf.writeBytes(m); // (2)
//        m.release();
//
//        if (buf.readableBytes() >= 4) { // (3)
//            long currentTimeMillis = (buf.readUnsignedInt() - 2208988800L) * 1000L;
//            System.out.println(new Date(currentTimeMillis));
//            ctx.close();
//        }
        UnixTime m = (UnixTime) msg;
        System.out.println(m);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}