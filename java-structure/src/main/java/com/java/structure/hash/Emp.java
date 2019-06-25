package com.java.structure.hash;

import lombok.Data;

/**
 * 雇员
 */
@Data
public class Emp {
    public int id;
    public String name;
    public Emp next; //next 默认为 null

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}