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
public @interface  RetryableRedisProcess {

    /**
     * 重试时删除的redis
     * @return
     */
    String[] retryRedisRemove() default {};
}
