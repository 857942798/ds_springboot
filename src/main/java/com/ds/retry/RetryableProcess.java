package com.ds.retry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hanfeng
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RetryableProcess {
    /**
     * 失败重试次数
     * @return
     */
    int maxAttempts() default 3;

    /**
     * 重试策略、操作
     * @return
     */
    BackoffProcess backoff() default @BackoffProcess;

}
