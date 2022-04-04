package com.ds.retry;

/**
 * 异常枚举基类
 * @author hanfeng
 *
 */
public interface IErrorEnum {

    String getCode();

    String getDescription();

    default String codeMsg() {
        return getCode()+" "+ getDescription();
    }
}