package com.java.cn.stream;

import java.util.Optional;

import com.java.cn.bean.Employee;

public class OptionalTest {

	public static void main(String[] args) {
		
//		Optional.of(null);
//		Optional<Employee> op = Optional.ofNullable(null);
//		Optional<Employee> op = Optional.empty();
//		Optional<Employee> op = Optional.of(new Employee());
//		Optional<Employee> op = Optional.ofNullable(new Employee());
		Optional<Employee> op = Optional.ofNullable(new Employee("java", 20, 20000.01));
//		op.orElseThrow();
//		if (op.isPresent()) {
//			System.out.println(op.get());
//		}
		
//		Employee emp = op.orElse(new Employee("bob", 10, 20.01));
		Employee emp = op.orElseGet(()->{return new Employee("bob", 10, 20.01);});
		System.out.println(emp);
	}

}
