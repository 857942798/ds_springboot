package com.ds.retry.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * 注解扫描类，@Retry
 * @author: dongsheng
 * @CreateTime: 2022/4/22
 * @Description:
 */
@Slf4j
public class RetryScannerRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    /**
     * 外部retry注解的基础包，是 @RetryScan 的哪个属性
     */
    private static final String RETRY_SCANNER_BASE_PACKAGE_FIELD = "basePackages";

    /**
     * 内部扫描的基础包列表
     */
    private static final String[] INNER_SCANNER_BASE_PACKAGES = {"com.ais.udep"};

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //扫描注解
//        Map<String, Object> annotationAttributes = importingClassMetadata
//                .getAnnotationAttributes(RetryScan.class.getName());
//        com.ais.udep.core.spring.UdepScanner retryScanner = new com.ais.udep.core.spring.UdepScanner(registry, RetryEnable.class);
//        com.ais.udep.core.spring.UdepScanner defaultRetryScanner = new com.ais.udep.core.spring.UdepScanner(registry, RetryEnable.class);
//        if (resourceLoader != null) {
//            retryScanner.setResourceLoader(resourceLoader);
//            defaultRetryScanner.setResourceLoader(resourceLoader);
//        }
//        String[] retryBasePackages = (String[]) annotationAttributes.get(RETRY_SCANNER_BASE_PACKAGE_FIELD);
//        int retryCount=0;
//        if(retryBasePackages !=null&&retryBasePackages.length!=0){
//            retryCount = retryScanner.scan(retryBasePackages);
//        }
//        int innerRetryCount = defaultRetryScanner.scan(INNER_SCANNER_BASE_PACKAGES);
//        log.info(StrUtil.format("retryScanner. packages={}, count={}", INNER_SCANNER_BASE_PACKAGES, retryCount+innerRetryCount));
    }
}
