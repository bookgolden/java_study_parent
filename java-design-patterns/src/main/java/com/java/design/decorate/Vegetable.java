package com.java.design.decorate;

public class Vegetable extends Food {
    private Food basic_food;

    public Vegetable(Food basic_food) {
        this.basic_food = basic_food;
    }

    public String make() {
        System.out.println("Vegetable");
        return basic_food.make() + "+蔬菜";
    }
}