package com.ds.netty;

import com.ds.netty.udp.UDPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: dongsheng
 * @CreateTime: 2022/2/15
 * @Description:
 */
@RestController
public class udpTest {

    @RequestMapping("send/{msg}")
    public void sendMsg(@PathVariable String msg){
        UDPClient udpClient=new UDPClient();
        udpClient.bind(8766,"127.0.0.1",6678,"127.0.0.1");
        udpClient.send(msg);
    }
}
