package com.ds.designPattern.statepattern;

/**
 * <p>
 *
 * </p>
 *
 * @author dongsheng
 * @date 2022/7/22
 */
public class StartState implements State {

    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    public String toString(){
        return "Start State";
    }
}
