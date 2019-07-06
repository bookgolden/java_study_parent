package com.java.design.test;

public abstract class Shape {

	protected int age;

	protected Shape(int age) {
		super();
		this.age = age;
	}
	
	public abstract void draw();
	
}
