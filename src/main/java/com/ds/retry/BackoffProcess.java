package com.ds.retry;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hanfeng
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BackoffProcess {

    /**
     * 初始重试间隔毫秒数
     * @return
     */
    long value() default 0L;

    /**
     * 重试最大延迟
     * @return
     */
    long maxDelay() default 0L;

    /**
     * 重试乘数
     * @return
     */
    double multiplier() default 0.0D;

    /**
     * 根据失败错误码code开启重试，不传默认失败执行
     * @return
     */
    String[] retryExceptionCode() default "";

    /**
     * redis策略
     * @return
     */
    RetryableRedisProcess redisOperation() default @RetryableRedisProcess;

}
