package com.java.cn.stream;

import java.util.Optional;

import com.java.cn.bean.Employee;

public class OptionalTest {

	public static void main(String[] args) {
		
//		Optional.of(null);
//		Optional<Employee> op = Optional.empty();
//		if(op.isPresent()){
//			System.out.println(op.get());
//		}
		
//		Optional<Employee> op = Optional.of(new Employee());
//		System.out.println(op.get());
		
//		Optional<Employee> op = Optional.ofNullable(new Employee());
//		if(op.isPresent()){
//			System.out.println(op.get());
//		}
		
//		Optional<Employee> op = Optional.ofNullable(new Employee("bob", 10, 20.01));
//		if(op.isPresent()){
//			System.out.println(op.get());
//		}
		
		Optional<Employee> op = Optional.ofNullable(null);
		System.out.println(op.get());
		
		
//		Employee emp = op.orElse(new Employee("bob", 10, 20.01));
//		System.out.println(emp);
		
//		Employee emp = op.orElseGet(()-> {});
	}

}
