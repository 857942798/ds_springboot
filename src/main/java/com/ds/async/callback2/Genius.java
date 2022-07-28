package com.ds.async.callback2;

/**
 * @author: dongsheng
 * @CreateTime: 2022/5/16
 * @Description:
 */
public class Genius {
    public void answer(CallBack callBack) {
        System.out.println("在忙其他事...");
        try {
            Thread.sleep(2000);
            System.out.println("忙完其他事，开始计算...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("天才计算出答案为：2");
        // 回调告诉你
        callBack.callback("2");
    }
}
