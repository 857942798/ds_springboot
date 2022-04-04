package com.ds.designPattern.test;

import lombok.Data;

/**
 * @author: dongsheng
 * @CreateTime: 2022/2/21
 * @Description:
 */
@Data
public class IncrReq extends BaseMsg{
    private String table_info;
    private String inc_seq;
    private String count;
}
