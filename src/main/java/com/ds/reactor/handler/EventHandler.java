package com.ds.reactor.handler;

import com.ds.reactor.bean.Event;
import com.ds.reactor.bean.InputSource;
import lombok.Data;

/**
 * @author: dongsheng
 * @CreateTime: 2022/6/15
 * @Description: event处理器的抽象类
 */
@Data
public abstract class EventHandler {
    private InputSource source;
    public abstract void handle(Event event);

}
