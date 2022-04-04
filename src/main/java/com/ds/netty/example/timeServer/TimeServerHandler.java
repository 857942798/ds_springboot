package com.ds.netty.example.timeServer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/7
 * @Description:
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当连接被建立后channelActive()方法会被调用，
     * @param ctx
     */
    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        /**
         *

        // 要发送一个新的消息，需要分配一个新的buffer(缓冲区) 去包含这个消息。
        // 我们要写一个32位的整数，因此缓冲区ByteBuf的容量至少是4个字节。
        // 通过ChannelHandlerContext.alloc()获取ByteBufAllocator(字节缓冲区分配器)，
        // 用他来分配一个新的buffer
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
        // ChannelFuture表示一个还没发生的 I/O 操作。这意味着你请求的一些 I/O 操作可能还没被处理，
        // 因为 netty 中所有的操作都是异步的。
        // 需加注意，close()方法可能不会立即关闭链接，同样close()也会返回一个ChannelFuture
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                ctx.close();
            }
        }); // (4)
         */
        ChannelFuture f = ctx.writeAndFlush(new UnixTime());
        f.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}