package com.ds.netty.dubbo.server;

import com.ds.netty.dubbo.service.HandlerServiceOne;
import com.ds.netty.dubbo.service.HandlerServiceTwo;
import com.ds.netty.dubbo.service.impl.HandlerServiceOneImpl;
import com.ds.netty.dubbo.service.impl.HandlerServiceTwoImpl;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/18
 * @Description:
 */
public class ServerBootstrap {

    public static Map<String,Object> serviceMap = new HashMap<>();

    public static void main(String[] args) {
        //两个接口的全类名
        String handlerServiceTwo = HandlerServiceTwo.class.getName();
        String handlerServiceOne = HandlerServiceOne.class.getName();
        //注册服务
        serviceMap.put(handlerServiceTwo,new HandlerServiceTwoImpl());
        serviceMap.put(handlerServiceOne,new HandlerServiceOneImpl());

        startServer("127.0.0.1", 7000);
    }

    private static void startServer(String hostname, int port) {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            io.netty.bootstrap.ServerBootstrap serverBootstrap = new io.netty.bootstrap.ServerBootstrap();

            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                                      @Override
                                      protected void initChannel(SocketChannel ch) throws Exception {
                                          ChannelPipeline pipeline = ch.pipeline();
                                          pipeline.addLast(new StringDecoder());
                                          pipeline.addLast(new StringEncoder());
                                          pipeline.addLast(new NettyServerHandler()); //业务处理器

                                      }
                                  }

                    );

            ChannelFuture channelFuture = serverBootstrap.bind(hostname, port).sync();
            System.out.println("服务提供方开始提供服务~~");
            channelFuture.channel().closeFuture().sync();

        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}


