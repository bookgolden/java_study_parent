package com.java.jdbc.bean;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/*
 * ORM编程思想  （object relational mapping）
 * 一个数据表对应一个java类
 * 表中的一条记录对应java类的一个对象
 * 表中的一个字段对应java类的一个属性
 * 
 */
@Setter
@Getter
public class Customer {
	
	private int id;
	private String name;
	private String email;
	private Date birth;
	public Customer() {
		super();
	}
	public Customer(int id, String name, String email, Date birth) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", birth=" + birth + "]";
	}
	
}
