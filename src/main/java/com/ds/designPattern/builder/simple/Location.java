package com.ds.designPattern.builder.simple;

/**
 * @author: dongsheng
 * @CreateTime: 2022/4/12
 * @Description:
 * Person中引用的属性
 */
public class Location {
    String street;
    String roomNo;

    public Location(String street, String roomNo) {
        this.street = street;
        this.roomNo = roomNo;
    }
}

