package com.java.design.observer;

public class LaoWang implements Person {
    private String name = "老王";

    @Override
    public void getMessage(String s) {
        System.out.println(name + "收到小美的信息，内容：" + s);
    }
}
