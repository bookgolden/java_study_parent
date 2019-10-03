package com.java.cn;


public class Person {
	private String firstName;
	private String lastName;

	public Person() {
	}

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Person getDefaultPerson() {
		return new Person("ryo", "12222");
	}
}
