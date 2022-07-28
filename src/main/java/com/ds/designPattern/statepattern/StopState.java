package com.ds.designPattern.statepattern;

/**
 * <p>
 *
 * </p>
 *
 * @author dongsheng
 * @date 2022/7/22
 */
public class StopState implements State {

    public void doAction(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    public String toString(){
        return "Stop State";
    }
}
