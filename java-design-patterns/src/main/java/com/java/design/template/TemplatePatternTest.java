package com.java.design.template;

public class TemplatePatternTest {
    public static void main(String[] args) {
        Game cricket = new Cricket();
        cricket.play();

        System.out.println("");

        Game footBall = new Football();
        footBall.play();
    }
}
