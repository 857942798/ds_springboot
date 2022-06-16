package com.ds.codeOptimize.switchcase.method2;

import java.lang.annotation.*;

/**
 * @author: dongsheng
 * @CreateTime: 2022/6/16
 * @Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CaseAnnotation {
    CaseEnum value();
}
