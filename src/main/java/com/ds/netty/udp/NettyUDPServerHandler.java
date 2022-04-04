package com.ds.netty.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

/**
 * 对于新消息进行操作的类
 * UDP服务端
 * 消息处理类
 */
public class NettyUDPServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    /**
     * 重写接收到的数据的具体操作
     * @param channelHandlerContext
     * @param datagramPacket
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        System.out.println("收到数据："  + datagramPacket.content().toString(CharsetUtil.UTF_8) );
    }

    /**
     * 出错回调
     * @param channelHandlerContext
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) throws Exception {
        cause.printStackTrace();
        channelHandlerContext.close();
    }
}
