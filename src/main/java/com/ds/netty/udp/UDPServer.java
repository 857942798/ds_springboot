package com.ds.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.springframework.stereotype.Service;

/**
 * UDP的服务端类
 */
@Service
public class UDPServer {
    private Bootstrap bootstrap;
    private NioEventLoopGroup group; //工作组
    private Channel channel; //信道
    private int port;
    private String address;

    /**
     * 线程的具体执行内容
     */
    class Listen implements Runnable {
        @Override
        public void run(){
            Thread.currentThread().setName("等待c客户端的消息进程");
            System.out.println("正在监听" + port + "端口，等待客户端消息。");
            try {
                group = new NioEventLoopGroup();
                bootstrap = new Bootstrap();
                bootstrap.group(group)
                        .channel(NioDatagramChannel.class)
                        .option(ChannelOption.SO_BROADCAST, true)
                        .option(ChannelOption.SO_RCVBUF, 1024 * 1024 * 100)
                        .handler(new NettyUDPServerHandler());
                channel = bootstrap.bind(address, port).sync().channel();
                channel.closeFuture().await();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                group.shutdownGracefully();
            }
        }
    }

    /**
     * 启动服务端
     */
    public void start() {
        Listen ser = new Listen();
        Thread m = new Thread(ser);
        m.start();
    }

    /**
     * 设定端口和ip
     * @param address
     * @param port
     * @return
     */
    public UDPServer bind(String address, int port) {
        this.address = address;
        this.port = port;
        return this;
    }
}
