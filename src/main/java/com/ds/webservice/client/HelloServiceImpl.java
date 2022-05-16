
package com.ds.webservice.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "HelloServiceImpl", targetNamespace = "http://impl.webservice.ds.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface HelloServiceImpl {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "hello", targetNamespace = "http://impl.webservice.ds.com/", className = "client.Hello")
    @ResponseWrapper(localName = "helloResponse", targetNamespace = "http://impl.webservice.ds.com/", className = "client.HelloResponse")
    @Action(input = "http://impl.webservice.ds.com/HelloServiceImpl/helloRequest", output = "http://impl.webservice.ds.com/HelloServiceImpl/helloResponse")
    public String hello(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "send", targetNamespace = "http://impl.webservice.ds.com/", className = "client.Send")
    @ResponseWrapper(localName = "sendResponse", targetNamespace = "http://impl.webservice.ds.com/", className = "client.SendResponse")
    @Action(input = "http://impl.webservice.ds.com/HelloServiceImpl/sendRequest", output = "http://impl.webservice.ds.com/HelloServiceImpl/sendResponse")
    public String send(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}