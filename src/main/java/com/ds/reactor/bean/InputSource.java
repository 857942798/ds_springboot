package com.ds.reactor.bean;

import lombok.AllArgsConstructor;

/**
 * @author: dongsheng
 * @CreateTime: 2022/6/15
 * @Description: 输入对象，reactor模式中处理的原始输入对象
 */
@AllArgsConstructor
public class InputSource {
    private Object data;
    private long id;

    @Override
    public String toString() {
        return "InputSource{" +
                "data=" + data +
                ", id=" + id +
                '}';
    }
}
