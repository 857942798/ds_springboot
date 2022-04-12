package com.ds.designPattern.builder.simple;

/**
 * @author: dongsheng
 * @CreateTime: 2022/4/12
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        // 下面.后面的不想要的可以注掉
        Person p = new Person.PersonBuilder()
                .basicInfo(1,"zhangsan",18)
                // .score(20)
                .weight(200)
                // .loc("bj","23")
                .build();
        System.out.println(p);
    }
}
