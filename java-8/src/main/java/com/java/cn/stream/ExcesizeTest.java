package com.java.cn.stream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.java.cn.bean.Employee;
import com.java.cn.bean.Employee.Status;

public class ExcesizeTest {
	
	static List<Employee> employee = Arrays.asList(
			new Employee("张三", 18, 9999.99, Status.FREE, new BigDecimal(10)),
			new Employee("李四", 38, 5555.99, Status.BUSY, new BigDecimal(20)),
			new Employee("王五", 50, 6666.66, Status.VOCATION, new BigDecimal(10)),
			new Employee("赵六", 16, 3333.33, Status.FREE, new BigDecimal(10)),
			new Employee("田七", 8, 7777.77, Status.BUSY, new BigDecimal(10))
		);
	
	public static void main(String[] args) {
//		Integer[] num = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
//		Arrays.stream(num).map((x) -> x * x).forEach(System.out::println);
		
//		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//		list.stream().map((x) -> {
//			return x * x;
//		}).forEach(System.out::println);
		
//		Stream<Integer> vsList = list.stream().map((x) -> {
//			return x * x;
//		});
//		vsList.forEach(System.out::println);
		
//		list = vsList.collect(Collectors.toList());
//		for(Integer i : list){
//			System.out.println(i);
//		}
		
//		long count = employee.stream().filter((x)->x.getStatus().equals(Status.FREE)).count();
//		System.out.println(count);
		
//		employee.stream().filter((x)->x.getStatus().equals(Status.FREE)).forEach(System.out::println);
//		employee.stream().map((x)->1).forEach(System.out::println);
		
//		employee.stream().map(x -> x.getName()).sorted((x, y) -> y.compareTo(x)).forEach(System.out::println);
//		employee.stream().map(x -> x.getAge()).sorted((x, y) -> Integer.compare(x, y));
		
		//求和
//		Optional<Integer> opt = employee.stream().map((x)->2).reduce(Integer::sum);
//		System.out.println(opt.get());

//		Double opt = employee.stream().map(e -> e.getSalary()).reduce(0.0, Double::sum);
//		System.out.println(opt);
		
//		BigDecimal opt = employee.stream().map(e -> e.getMoney()).reduce(new BigDecimal(0.0), BigDecimal::add);
//		System.out.println(opt);
		
//		String str = employee.stream().map(x -> x.getName()).reduce("", String::concat);
//		String str2 = employee.stream().map(x -> x.getName()).reduce("", (x, y) -> x.concat(y)).replace("田七", "bob");
//		System.out.println(str);
//		System.out.println(str2);
		
//		String str = employee.stream().map(x->x.getName()).collect(Collectors.joining(",", "---==", "==---"));
//		System.out.println(str);
		
//		employee.stream().map(x->x.getName()).flatMap(ExcesizeTest::filterCharacter)
//						.forEach(System.out::print);
		
//		//找出最高的金额
//		Optional<Double> max = employee.stream().map(e -> e.getSalary()).max((x, y)->Double.compare(x, y));
//		System.out.println(max.get());
		
//		Optional<Double> min = employee.stream().map(e -> e.getSalary()).min(Double::compare);
//		System.out.println(min.get());
		
	}
	
//	public static Stream<String> filterCharacter(String str){
//		List<String> list = new ArrayList<>();
//		for(Character ch : str.toCharArray()){
//			list.add(ch.toString());
//		}
//		return list.stream();
//	}
	
	
}
