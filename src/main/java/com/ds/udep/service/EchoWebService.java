package com.ds.udep.service;

import com.ais.udep.core.message.UdepRequest;
import com.ais.udep.server.netty.channel.ChannelManage;
import com.ais.udep.server.netty.send.UdepServerSender;
import com.ais.udep.server.netty.webService.demo.Echo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Simple Web Service sample.
 */
@WebService(name = "echo", portName = "echoPort",
        serviceName = "echoService", targetNamespace = "http://ais.echo")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE,
        style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public class EchoWebService {

    private UdepServerSender udepServerSender= UdepServerSender.getInstance();
    private ChannelManage channelManage=ChannelManage.getInstance();

    @WebMethod
    @WebResult(name = "echoResult", partName = "echoResult")
    public Echo echo(@WebParam(name = "echoRequest", partName = "echoRequest") Echo echo) {
        String value = echo.getValue();
        echo.setValue("Hello " + value);
        WebService annotation = this.getClass().getAnnotation(WebService.class);
        Map<String,Object> map=new HashMap<>();
        map.put("data",echo);
        map.put("serverName","echoService");
        Set<String> channelIds = channelManage.getChannelIdsByService(annotation.serviceName());
        channelIds.forEach(id->{
            udepServerSender.sendToClient(Long.valueOf(id), new UdepRequest(map));
        });
        return echo;
    }

}
