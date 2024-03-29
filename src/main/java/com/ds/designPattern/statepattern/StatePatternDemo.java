package com.ds.designPattern.statepattern;

/**
 * <p>
 *      状态模式
 * </p>
 *
 * @author dongsheng
 * @date 2022/7/22
 */
public class StatePatternDemo {
    public static void main(String[] args) {
        Context context = new Context();

        StartState startState = new StartState();
        startState.doAction(context);

        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);

        System.out.println(context.getState().toString());
    }
}
