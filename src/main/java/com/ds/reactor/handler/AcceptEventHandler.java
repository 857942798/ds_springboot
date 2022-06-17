package com.ds.reactor.handler;

import com.ds.reactor.bean.Event;
import com.ds.reactor.bean.EventType;
import com.ds.reactor.Selector;

/**
 * @author: dongsheng
 * @CreateTime: 2022/6/15
 * @Description: ACCEPT事件处理器
 */
public class AcceptEventHandler extends EventHandler {
    private Selector selector;

    public AcceptEventHandler(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void handle(Event event) {
        //处理Accept的event事件
        if (event.getType() == EventType.ACCEPT) {

            //TODO 处理ACCEPT状态的事件

            //将事件状态改为下一个READ状态，并放入selector的缓冲队列中
            Event readEvent = new Event();
            readEvent.setSource(event.getSource());
            readEvent.setType(EventType.READ);

            selector.addEvent(readEvent);
        }
    }
}
