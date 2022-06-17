package com.ds.reactor.bean;

import lombok.Data;

/**
 * @author: dongsheng
 * @CreateTime: 2022/6/15
 * @Description: reactor模式中内部处理的event类
 */
@Data
public class Event {
    private InputSource source;
    private EventType type;
}
