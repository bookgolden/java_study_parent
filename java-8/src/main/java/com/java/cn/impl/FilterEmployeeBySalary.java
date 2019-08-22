package com.java.cn.impl;

import com.java.cn.bean.Employee;
import com.java.cn.interfaces.MyPredicate;

public class FilterEmployeeBySalary implements MyPredicate<Employee> {

	@Override
	public boolean test(Employee t) {
		return t.getSalary() > 6666;
	}

}
