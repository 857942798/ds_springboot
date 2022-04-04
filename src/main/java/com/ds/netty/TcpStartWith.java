package com.ds.netty;

import com.ds.netty.tcp.NettyClient;
import com.ds.netty.tcp.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.net.InetSocketAddress;


/**
 * 启动Spring boot 开始等待客户端消息
 */
@Component
public class TcpStartWith implements ServletContextAware {
    NettyServer nettyServer;
    NettyClient nettyClient;

    @Autowired
    public void setNettyServer(NettyServer nettyServer) {
        this.nettyServer = nettyServer;
    }
    @Autowired
    public void setNettyClient(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
    }

    @Override
    public void setServletContext(ServletContext servletContext){
        //发送
//        udpClient.bind(6679,"127.0.0.1",5678,"127.0.0.1")
//                .send("你好啊，这次没问题了吧？");
        //接收
        nettyServer.start();
    }
}
