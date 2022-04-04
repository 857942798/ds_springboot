package com.ds.designPattern.test;

import lombok.Data;

/**
 * @author: dongsheng
 * @CreateTime: 2022/2/21
 * @Description:
 */
@Data
public class BaseMsg {
    private String msgId;
    private String opCode;
    private Object data;
}
