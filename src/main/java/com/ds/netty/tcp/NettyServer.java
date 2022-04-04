package com.ds.netty.tcp;

import com.ds.netty.udp.NettyUDPServerHandler;
import com.ds.netty.udp.UDPServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

@Service
public class NettyServer {
    /**
     * 线程的具体执行内容
     */
    class TcpListen implements Runnable {
        @Override
        public void run(){
            InetSocketAddress socketAddress=new InetSocketAddress("127.0.0.1",5678);

            //主线程，先创建一个
            EventLoopGroup bossGroup = new NioEventLoopGroup(1);
            //工作线程
            EventLoopGroup workGroup = new NioEventLoopGroup(200);
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerChannelInitializer())
                    .localAddress(socketAddress)
                    //设置队列大小
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    //两小时没有通讯，发送探测数据报文
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            try {
                ChannelFuture future = bootstrap.bind(socketAddress).sync();
                System.out.println("服务器开始监听端口：" + socketAddress.getPort());
                future.channel().closeFuture().sync();
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                //关闭两个部分工作组
                bossGroup.shutdownGracefully();
                workGroup.shutdownGracefully();
            }
        }
    }

    public void start(){
        TcpListen ser = new TcpListen();
        Thread m = new Thread(ser);
        m.start();
    }
}
