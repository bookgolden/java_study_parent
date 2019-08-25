package com.java.cn.type;

enum COLOR {
	WHITE, BLACK, YELLOW
}

public class Person {
	private int age;
	private String name;
	private COLOR color;

	public Person() {

	}

	private Person(int age, String name) {
		this.age = age;
		this.name = name;
	}

	private Person(Integer age, String name) {
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer hello(Integer age) {
		return age;
	}

	public String hello(Integer age, String name) {
		return "two paramter";
	}
	
	public Person hello(Integer age, String name, COLOR color) {
		return new Person(age, name);
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + ", color=" + color + "]";
	}
}