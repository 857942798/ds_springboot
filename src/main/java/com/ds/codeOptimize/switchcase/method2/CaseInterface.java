package com.ds.codeOptimize.switchcase.method2;

import java.util.Map;

/**
 * @author: dongsheng
 * @CreateTime: 2022/6/16
 * @Description: 分支统一接口
 */
public interface CaseInterface {
    String execute(Map<String,Object> data) throws Exception;
}
