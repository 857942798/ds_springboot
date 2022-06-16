package com.ds.codeOptimize.switchcase.method2;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: dongsheng
 * @CreateTime: 2022/6/16
 * @Description: accept分支
 */
@Component
@CaseAnnotation(value = CaseEnum.ACCEPT)
public class AcceptImpl implements CaseInterface{
    @Override
    public String execute(Map<String, Object> data) throws Exception {
        return "接收逻辑处理";
    }
}
