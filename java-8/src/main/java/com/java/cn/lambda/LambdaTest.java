package com.java.cn.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import com.java.cn.bean.Employee;
import com.java.cn.impl.FilterEmployeeByAge;
import com.java.cn.impl.FilterEmployeeBySalary;
import com.java.cn.interfaces.MyPredicate;

public class LambdaTest {

	static List<Employee> employee = Arrays.asList(
			new Employee("张三", 18, 9999.99),
			new Employee("李四", 38, 5555.99),
			new Employee("王五", 50, 6666.66),
			new Employee("赵六", 16, 3333.33),
			new Employee("田七", 8, 7777.77));
	
	public static void main(String[] args) {
		
		String vs = "abc".concat("123");
		System.out.println(vs);
//		test2();
//		test3();
//		test4();
//		test5();
	}
	
	// 匿名内部类
	public void test1() {
		Comparator<Integer> com = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		};
		TreeSet<Integer> ts = new TreeSet<>(com);
	}

	// Lambda表达式
	public static void test2() {
		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
		TreeSet<Integer> ts = new TreeSet<>(com);
	}
	
	//需求：获取当前公司中员工年龄大于35的员工信息
	public static List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> emp){
		List<Employee> emps = new ArrayList<>();
		for(Employee e : list){
			if(emp.test(e)){
				emps.add(e);
			}
		}
		return emps;
	}

	public static void test3() {
		List<Employee> list = filterEmployee(employee, new FilterEmployeeByAge());
		for(Employee e : list){
			System.out.println(e);
		}
		
		System.out.println("--------------");
		
		List<Employee> list2 = filterEmployee(employee, new FilterEmployeeBySalary());
		for(Employee e : list2){
			System.out.println(e);
		}
		
		System.out.println("--------------");
		
		//匿名内部类方式
		List<Employee> list3 = filterEmployee(employee, new MyPredicate<Employee>() {
			@Override
			public boolean test(Employee t) {
				return t.getSalary() < 5555;
			}
		});
		for(Employee e : list3){
			System.out.println(e);
		}
	}

	//优化方式三: Lambda表达式
	public static void test4() {
		List<Employee> list = filterEmployee(employee, (e)->e.getAge()<20);
		for(Employee e : list){
			System.out.println(e);
		}
	}
	
	//优化方式四:
	public static void test5() {
		employee.stream()
				.filter((e) -> e.getSalary() >= 7777.9)
				.limit(2)
				.forEach(System.out::println);
	}
	
}