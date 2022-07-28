package com.ds.retry.annotation;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;

/**
 * @author: dongsheng
 * @CreateTime: 2022/4/22
 * @Description:
 */
public class UdepScanner extends ClassPathBeanDefinitionScanner {

    public UdepScanner(BeanDefinitionRegistry registry, Class<? extends Annotation>... annotationTypes) {
        super(registry);
        for (Class<? extends Annotation> annotationType : annotationTypes) {
            super.addIncludeFilter(new AnnotationTypeFilter(annotationType));
        }
    }
}
