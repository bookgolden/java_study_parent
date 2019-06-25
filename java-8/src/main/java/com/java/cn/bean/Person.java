package com.java.cn.bean;

import lombok.Data;

@Data
public class Person {

	public Person() {
	}

	public Person(String firstName, String lastName, int age, String sex) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.sex = sex;
	}

	private String firstName;
	private String lastName;
	private int age;
	private String sex;

}
