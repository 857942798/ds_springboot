package com.ds.designPattern.statepattern;

/**
 * <p>
 *
 * </p>
 *
 * @author dongsheng
 * @date 2022/7/22
 */
public class Context {
    private State state;

    public Context(){
        state = null;
    }

    public void setState(State state){
        this.state = state;
    }

    public State getState(){
        return state;
    }
}
