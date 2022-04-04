package com.ds.netty;

import com.ds.netty.tcp.NettyClient;
import com.ds.netty.udp.UDPClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: dongsheng
 * @CreateTime: 2022/2/15
 * @Description:
 */
@RestController
public class TcpTest {
   private NettyClient nettyClient=new NettyClient();

    @RequestMapping("/tcp/send/{msg}")
    public String sendMsg(@PathVariable String msg){
        nettyClient.send(msg);
        return "ss";
    }
}
