package com.java.cn.bigdecimal;

class InfoImpl<T, K, U> implements Info<U> {    // 定义泛型接口的子类
    private U var;
    private T x;
    private K y;

    public InfoImpl(U var) {        // 通过构造方法设置属性内容
        this.setVar(var);
    }

    public void setVar(U var) {
        this.var = var;
    }

    public U getVar() {
        return this.var;
    }
}