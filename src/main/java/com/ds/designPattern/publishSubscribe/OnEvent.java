package com.ds.designPattern.publishSubscribe;

/**
 * @author: dongsheng
 * @CreateTime: 2022/3/7
 * @Description:
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OnEvent {
    EventTypeEnum eventType() default EventTypeEnum.EVENT_NONE;
}
