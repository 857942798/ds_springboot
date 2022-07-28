package com.ds.retry.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: dongsheng
 * @CreateTime: 2022/4/22
 * @Description:
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RetryScannerRegistrar.class)
public @interface RetryScan {
    String[] basePackages();
}
