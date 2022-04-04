package com.ds.designPattern.publishSubscribe;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/7
 * @Description: 事件类型
 */
public enum EventTypeEnum {
    EVENT_NONE("noneEvent"),
    EVENT_TIMER("timer"),
    EVENT_TEST("test");

    String value;

    EventTypeEnum(String value) {
        this.value = value;
    }
}
