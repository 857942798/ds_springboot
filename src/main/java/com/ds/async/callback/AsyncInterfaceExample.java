package com.ds.async.callback;

import java.util.concurrent.CompletableFuture;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/25
 * @Description: callback示例
 */
public interface AsyncInterfaceExample {
    // 同步
    String computeSomeThine();

    // 异步
    CompletableFuture<String> computeSomeThingAsync();
}
