package com.ds.codeOptimize.switchcase.method2;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: dongsheng
 * @CreateTime: 2022/6/16
 * @Description: 获取spring应用上下文工具类
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context=context;
    }

    //获取spring应用上下文（容器）-进而准备获取相应的bean
    public ApplicationContext getContext(){
        return context;
    }
}
