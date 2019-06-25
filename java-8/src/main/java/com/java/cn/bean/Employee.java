package com.java.cn.bean;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Employee {
	
	private String name;
	private int age;
	private double salary;
	private Status status;

	public Employee() {
		super();
	}

	public Employee(String name, int age, double salary) {
		super();
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public Employee(String name, int age, double salary, Status status) {
		super();
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.status = status;
	}

	public enum Status {
		VS, FREE, BUSY, VOCATION;
	}

}