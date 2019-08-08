package com.java.design.decorate;

public class Cream extends Food {
    private Food basic_food;

    public Cream(Food basic_food) {
        this.basic_food = basic_food;
    }

    public String make() {
        System.out.println("Cream");
        return basic_food.make() + "+奶油";
    }
}