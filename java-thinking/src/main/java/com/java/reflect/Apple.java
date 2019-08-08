package com.java.reflect;

import java.lang.reflect.Field;

public class Apple {

	private int price;
	public int age;
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public static void main(String[] args) throws Exception {
		Class clz = Apple.class;
		Field[] fields = clz.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName());
		}
	}
}