package com.ds.rxjava;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

import java.util.concurrent.TimeUnit;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/17
 * @Description:
 */
@Slf4j
public class HelloWorld {
    public static void main(String[] args) {
        // 1. 创建被观察者
        Observable<Object> observable = Observable.create(subscriber -> {
            subscriber.onNext("Hello world.");
            subscriber.onNext("Hello world22");
            subscriber.onCompleted();
            throw new NullPointerException("Throw a Exception...");
        });

        // 2. 创建观察者
        Subscriber<Object> subscriber = new Subscriber<Object>() {


            @Override
            public void onCompleted() {
                log.info("onCompleted...");
            }

            @Override
            public void onError(Throwable e) {
                log.info("onError...");
            }

            @Override
            public void onNext(Object o) {
                log.info("onNext: {}", o);
            }

        };

        // 3. 订阅事件
        observable.subscribe(subscriber);
    }
}
