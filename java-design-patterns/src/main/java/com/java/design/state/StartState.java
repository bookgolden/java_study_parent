package com.java.design.state;

public class StartState implements State {

    @Override
    public void doAction(Context context) {
        System.out.println("StartState...........");
        context.setState(this);
    }

}
