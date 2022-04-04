package com.ds.netty.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

/**
 * 操作发送消息之后，接收的消息操作类
 */
public class NettyUDPClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    /**
     * 操作接收到的消息
     * @param channelHandlerContext
     * @param datagramPacket
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        System.out.println(datagramPacket.content().toString());
//        channelHandlerContext.close();
    }

    /**
     * 通道读取完毕回调
     * @param channelHandlerContext
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.channelReadComplete(channelHandlerContext);
        System.out.println("关闭channel");
        channelHandlerContext.close();
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

