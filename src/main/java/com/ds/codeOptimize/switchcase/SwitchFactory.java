package com.ds.codeOptimize.switchcase;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author: dongsheng
 * @CreateTime: 2022/6/9
 * @Description:
 */
@Slf4j
public class SwitchFactory {
    Map<Integer, Function<Object, Object>> map = new HashMap<>();

    private static SwitchFactory instance;

    // 使用单例模式实例化当前工厂类实例
    public static SwitchFactory getInstance() {
        if (instance == null) {
            synchronized (SwitchFactory.class) {
                if (instance == null) {
                    instance = new SwitchFactory();
                }
            }
        }
        return instance;
    }

    SwitchFactory(){
        map.put(1, this::function1);
        map.put(2, this::function2);
        map.put(3, this::function3);
        map.put(4, this::function4);
    }

    public Function<Object, Object> getFunction(Integer type){
        return map.get(type);
    }

    private Object function1(Object data) {
        return "分支1";
    }

    private Object function2(Object data) {
        return "分支2";
    }

    private Object function3(Object data) {
        return "分支3";
    }

    private Object function4(Object data) {
        return "分支4";
    }

    public static void main(String[] args){
        SwitchFactory switchFactory = SwitchFactory.getInstance();
        // type可用枚举类代替
        Integer type=1;
        Map data=new HashMap();
        String res =(String) switchFactory
                                .getFunction(type)
                                    .apply(data);
        System.out.println(res);
    }

}
