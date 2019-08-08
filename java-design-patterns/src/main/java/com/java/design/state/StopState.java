package com.java.design.state;

public class StopState implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("StopState.........");
        context.setState(this);
    }
}
