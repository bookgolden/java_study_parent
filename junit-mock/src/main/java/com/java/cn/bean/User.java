package com.java.cn.bean;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;

    public User(){}

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}