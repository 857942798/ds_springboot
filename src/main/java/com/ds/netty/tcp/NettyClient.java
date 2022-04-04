package com.ds.netty.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class NettyClient {
    ChannelFuture future=null;

    public void send(String data) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap()
                .group(group)
                .option(ChannelOption.TCP_NODELAY, true)
                .channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer());
        try {
            if(future==null){
                future = bootstrap.connect("127.0.0.1", 5678).sync();
                System.out.println("客户端尝试连接");
            }
            future.channel().writeAndFlush(data);
            future.channel().closeFuture().sync();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }
}

