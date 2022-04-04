package com.ds.async.callback;

import java.util.concurrent.CompletableFuture;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/25
 * @Description:
 */
public class AsyncInterfaceExampleImpl implements AsyncInterfaceExample {
    @Override
    public String computeSomeThine() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "hello, world";
    }

    @Override
    public CompletableFuture<String> computeSomeThingAsync() {
        return CompletableFuture.supplyAsync(this::computeSomeThine);
    }
}
