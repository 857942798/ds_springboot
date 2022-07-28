package com.ds.retry.annotation;

import java.lang.annotation.*;

/**
 * @author: dongsheng
 * @CreateTime: 2022/4/22
 * @Description: 被扫描的类会被当做bean注册，其中被@Retry修饰的方法才具备重试功能
 */
@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RetryEnable {
    boolean value() default true;
}
