package com.ds.designPattern.observer;

/**
 * @author: dongsheng
 * @CreateTime: 2022/2/17
 * @Description:
 */
public class Client {
    public static void main(String[] args) {
        Observable observable = new Observable();
        observable.addObserver(new ConcreteObserver1());
        observable.addObserver(new ConcreteObserver2());
        observable.doSomething();
    }
}
