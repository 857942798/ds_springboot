package com.ds.netty.example.timeServer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/7
 * @Description:
 * 跟 DISCARD 和 ECHO 服务器不同，我们要写一个客户端程序应对 TIME 协议，因为你无法把一个32位整数翻译成日期。
 * 本节中，我们确保服务端正常工作，并学习如何使用 netty 写一个客户端程序。
 * netty 客户端和服务器最大的不同在于，客户端使用了不同的Bootstrap和Channel实现类。请看下面的例子：
 */
public class TimeClient {

    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        int port = 8080;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    channel.pipeline().addLast(new TimeDecoder(),new TimeClientHandler());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
