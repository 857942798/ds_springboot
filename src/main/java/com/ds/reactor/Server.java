package com.ds.reactor;

import com.ds.reactor.bean.EventType;
import com.ds.reactor.handler.AcceptEventHandler;

/**
 * @author: dongsheng
 * @CreateTime: 2022/6/15
 * @Description:
 */
public class Server {
    Selector selector = new Selector();
    Dispatcher eventLooper = new Dispatcher(selector);
    Acceptor acceptor;

    Server(int port) {
        acceptor = new Acceptor(selector, port);
    }

    public void start() {
        eventLooper.registEventHandler(EventType.ACCEPT, new AcceptEventHandler(selector));
        new Thread(acceptor, "Acceptor-" + acceptor.getPort()).start();
        eventLooper.handleEvents();
    }
}

