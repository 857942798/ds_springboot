package com.ds.netty.udp;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

@Service
public class UDPClient {
    private int port;//绑定的端口
    private String address;//绑定的ip
    private int aim_port;//目标的端口
    private String aim_address;//目标的ip

    /**
     * 绑定目标和本地的ip以及端口
     * @param port
     * @param address
     * @param aim_port
     * @param aim_address
     * @return
     */
    public UDPClient bind(int port, String address, int aim_port, String aim_address){
        this.address = address;
        this.port = port;
        this.aim_address = aim_address;
        this.aim_port = aim_port;
        return this;
    }

    /**
     * 发送消息
     * @param data
     */
    public void send(String data) {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            Bootstrap clientBoostrap = new Bootstrap();
            clientBoostrap.group(eventExecutors)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    //接收缓冲区大小设置
                    .option(ChannelOption.SO_RCVBUF, 1024 * 1024 * 100)
                    //发送缓冲区大小设置
                    .option(ChannelOption.SO_SNDBUF, 1024 * 1024 * 100)
                    .handler(new NettyUDPClientHandler());
            //绑定本地的ip和端口
            Channel channel = clientBoostrap.bind(address, port).sync().channel();
//            发送消息
            channel.writeAndFlush(new DatagramPacket(
                    Unpooled.copiedBuffer(data, CharsetUtil.UTF_8),
                    new InetSocketAddress(aim_address, aim_port))).sync();
//            等待15秒后，关闭
            channel.closeFuture().await(5000);

//            ChannelFuture channelFuture = clientBoostrap.connect("localhost", 6678);
//            channelFuture.channel().writeAndFlush(new DatagramPacket(
//                    Unpooled.copiedBuffer(data, CharsetUtil.UTF_8),
//                    new InetSocketAddress(aim_address, aim_port))).sync();
//            System.out.println("通道id:" + channelFuture.channel().id().toString());
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("客户端关闭了");
            eventExecutors.shutdownGracefully();
        }
    }
}

