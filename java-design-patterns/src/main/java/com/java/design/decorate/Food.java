package com.java.design.decorate;

public class Food {
    private String food_name;

    public Food() {
    }

    public Food(String food_name) {
        this.food_name = food_name;
    }

    public String make() {
        System.out.println("Food");
        return food_name;
    }

    ;
}
