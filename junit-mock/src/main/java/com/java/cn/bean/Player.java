package com.java.cn.bean;

/**
 * 玩家
 */
public class Player {
    // 编号
    private final int id;
    // 名字
    private String name;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
