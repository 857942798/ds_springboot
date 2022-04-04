package com.ds.netty.example.echoServer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/7
 * @Description:
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        ByteBuf in = (ByteBuf) msg;
        try {
            System.out.println(in.toString(io.netty.util.CharsetUtil.UTF_8));
            //因为在写数据时ctx.write(msg)，netty 已经帮你释放它了
            ctx.write(msg); // (1)
            //ctx.write()关没有把消息写到网络上，他在内部被缓存起来，你需要调用ctx.flush()把他刷新到网络上。ctx.writeAndFlush(msg)是个更简洁的方法。
            ctx.flush(); // (2)
        } finally {
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
