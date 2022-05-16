package com.ds.retry;

import org.aspectj.lang.annotation.Aspect;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/17
 * @Description:
 */
@Aspect
public class test {
    // 第三方token失效请求错误code
    static final String HTTP_TOKEN_FAIL = "000015";
    // 第三方超时请求错误code
    static final String REQUEST_TIME_OUT = "000016";
    //删除redis key
    static final String remove_redis_key = "ssss:redis:key";


    @RetryableProcess(maxAttempts = 2, backoff = @BackoffProcess(retryExceptionCode = {HTTP_TOKEN_FAIL, REQUEST_TIME_OUT}, redisOperation = @RetryableRedisProcess(retryRedisRemove = {remove_redis_key})))
    public static void test1(){
            throw new BtException("11","11");
    }

}
