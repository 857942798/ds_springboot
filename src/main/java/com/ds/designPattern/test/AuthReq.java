package com.ds.designPattern.test;

import lombok.Data;

/**
 * @author: dongsheng
 * @CreateTime: 2022/2/21
 * @Description:
 */
@Data
public class AuthReq extends BaseMsg{

    private String mark_port;
    private String mark_ip;
}
