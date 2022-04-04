package com.ds.netty;

import com.ds.netty.udp.UDPClient;
import com.ds.netty.udp.UDPServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;


/**
 * 启动Spring boot 开始等待客户端消息
 */
@Component
public class UdpStartWith implements ServletContextAware {
    UDPServer udpServer;
    UDPClient udpClient;

    @Autowired
    public void setUdpClient(UDPClient udpClient) {
        this.udpClient = udpClient;
    }
    @Autowired
    public void setUdpServer(UDPServer udpServer) {
        this.udpServer = udpServer;
    }

    @Override
    public void setServletContext(ServletContext servletContext){
        //发送
//        udpClient.bind(6679,"127.0.0.1",5678,"127.0.0.1")
//                .send("你好啊，这次没问题了吧？");
        //接收
        udpServer.bind("127.0.0.1", 6678).start();

    }
}
