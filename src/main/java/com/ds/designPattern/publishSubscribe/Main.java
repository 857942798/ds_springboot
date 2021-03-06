package com.ds.designPattern.publishSubscribe;

import java.util.concurrent.TimeUnit;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/7
 * @Description:
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        Subscriber subscriber = new Subscriber();
        EventDispatcher eventDispatcher = new EventDispatcher();
        eventDispatcher.subscribe(EventTypeEnum.EVENT_TIMER, subscriber);
        eventDispatcher.subscribe(EventTypeEnum.EVENT_TEST, subscriber);
        eventDispatcher.start();
        while (true) {
            TimeUnit.SECONDS.sleep(3);
            eventDispatcher.putEvent(new Event(EventTypeEnum.EVENT_TEST, "Block code test"));
        }
    }

}
