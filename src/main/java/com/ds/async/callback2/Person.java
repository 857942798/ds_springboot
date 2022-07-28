package com.ds.async.callback2;

/**
 * @author: dongsheng
 * @CreateTime: 2022/5/16
 * @Description:
 */
public class Person implements CallBack{
    private Genius genius;

    public Person(Genius genius) {
        this.genius = genius;
    }

    @Override
    public void callback(String string) {
        System.out.println("收到答案：" + string);
    }

    public void ask() {
        genius.answer(this);
    }

}
