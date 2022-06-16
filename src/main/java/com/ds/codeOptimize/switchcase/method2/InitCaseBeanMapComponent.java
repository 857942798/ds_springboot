package com.ds.codeOptimize.switchcase.method2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: dongsheng
 * @CreateTime: 2022/6/16
 * @Description: 实现枚举值和分支实现类的映射
 */
@Component
public class InitCaseBeanMapComponent {
    public static Map<CaseEnum,CaseInterface> processMap=new ConcurrentHashMap<>();

    @Autowired
    private SpringContextUtil springContextUtil;

    @PostConstruct
    public void init() {
        //获取那些带上了注解的 处理实现类
        Map<String,Object> map=springContextUtil.getContext().getBeansWithAnnotation(CaseAnnotation.class);

        //添加 case常量值~方法操作逻辑实现类 映射
        for (Object process:map.values()){
            CaseAnnotation annotation=process.getClass().getAnnotation(CaseAnnotation.class);
            processMap.put(annotation.value(),(CaseInterface) process);
        }
    }

    public Map<CaseEnum,CaseInterface> getProcessMap(){
        return processMap;
    }

}
