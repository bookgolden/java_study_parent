package com.java.cn.stream;

import java.util.Arrays;
import java.util.List;

import com.java.cn.bean.Employee;
import com.java.cn.bean.Employee.Status;

public class StreamTest3 {
	
	static List<Employee> employee = Arrays.asList(
			new Employee("张三", 18, 9999.99, Status.FREE),
			new Employee("李四", 38, 5555.99, Status.BUSY),
			new Employee("王五", 50, 6666.66, Status.VOCATION),
			new Employee("赵六", 16, 3333.33, Status.FREE),
			new Employee("田七", 8, 7777.77, Status.BUSY)
		);

	public static void main(String[] args) {
		
		/**
		 * 查找与匹配
		 * allMath--检查是否匹配所有元素
		 * anyMath--检查是否至少匹配一个元素
		 * noneMath--检查是否没有匹配所有元素
		 * findFirst--返回第一个元素
		 * findAny--返回当前流中的任意元素
		 * count--返回流中元素的总个数
		 * max--返回流中最大值
		 * min--返回流中最小值
		 */
		
//		boolean b1 = employee.stream().allMatch((e) -> e.getStatus().equals(Status.BUSY));
//		boolean b1 = employee.stream().anyMatch((e) -> e.getStatus().equals(Status.BUSY));
//		boolean b1 = employee.stream().noneMatch((e) -> e.getStatus().equals(Status.BUSY));
//		System.out.println(b1);
//		
//		Optional<Employee> op = employee.stream().sorted((x, y) -> Double.compare(x.getSalary(), x.getSalary())).findFirst();
//		Optional<Employee> op = employee.stream().filter((e)->e.getStatus().equals(Status.FREE)).findAny();
//		Optional<Employee> op = employee.parallelStream().filter((e)->e.getStatus().equals(Status.FREE)).findAny();
//		System.out.println(op.get());
//		
//		long count = employee.parallelStream().count();
//		System.out.println(count);
//		
//		Optional<Employee> max = employee.stream().max((x, y) -> Double.compare(x.getSalary(), y.getSalary()));
//		System.out.println(max.get());
//		
//		Optional<Double> min = employee.stream().map((x)->x.getSalary()).min((x, y)-> Double.compare(x, y));
//		Optional<Double> min = employee.stream().map(Employee::getSalary).min((x, y)-> Double.compare(x, y));
//		System.out.println(min.get());
		
		/**
		 * 归约
		 * reduce(T identity, BinaryOperator)
		 * reduce(BinaryOperator)--可以将流中元素反复结合起来，得到一个值
		 */
//		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
//		Integer sum = list.stream().reduce(0, (x, y)-> x + y);
//		System.out.println(sum);
//		
//		Optional<Double> salarySum = employee.stream().map(Employee::getSalary).reduce(Double::sum);
//		System.out.println(salarySum.get());
		
		/**
		 * 收集
		 * collect--将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
		 */
//		List<String> list1 = employee.stream().map(Employee::getName).collect(Collectors.toList());
//		list1.forEach(System.out::print);
//		String vs = list1.stream().collect(Collectors.joining(",", "", ""));
//		String vs = employee.stream().map(Employee::getName).collect(Collectors.joining(",", "", ""));
//		System.out.println("===  "+vs);
//		
//		Set<String> set = employee.stream().map(Employee::getName).collect(Collectors.toSet());
//		Set<String> hashSet = (Set) employee.stream().map(Employee::getName).collect(Collectors.toSet());
//		set.forEach(System.out::print);
//		
//		HashSet<String> hashSet = employee.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));	//?
//		System.out.println(hashSet.toString());
//		hashSet.forEach(System.out::print);
//		Map<Integer, Employee> ep = employee.stream().collect(Collectors.toMap(Employee::getAge, Function.identity()));
//		System.out.println(ep);
		
//		//总数
//		Long count = employee.stream().collect(Collectors.counting());
//		System.out.println(count);
//		//平均值
//		Double avg = employee.stream().collect(Collectors.averagingDouble(Employee::getSalary));
//		System.out.println(avg);
//		//总和
//		double sum = (double) employee.stream().collect(Collectors.summingDouble(Employee::getSalary));
//		System.out.println(sum);
//		//最大值
//		Optional<Employee> emp = employee.stream().collect(Collectors.maxBy((x, y)-> Double.compare(x.getSalary(), y.getSalary())));
//		Optional<Employee> emp = employee.stream().max((x, y) -> Double.compare(x.getSalary(), y.getSalary()));
//		System.out.println(emp.get());
//		
//		//最小值
//		Optional<Double> min = employee.stream().map(Employee::getSalary).collect(Collectors.minBy(Double::compare));
//		System.out.println(min.get());
//		
//		//分组
//		Map<Status, List<Employee>> map = employee.stream().collect(Collectors.groupingBy(Employee::getStatus));
//		System.out.println(map);
//		
//		//多级分组
//		Map<Status, Map<String, List<Employee>>> map = employee.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e->{
//			if(((Employee)e).getAge()<25){
//				return "青年";
//			}else{
//				return "中老年";
//			}
//		})));
//		System.out.println(map);
		
//		//分区
//		Map<Boolean,List<Employee>> map = employee.stream().collect(Collectors.partitioningBy((e)-> e.getSalary()>8000));
//		System.out.println(map.get(true));
//		
//		DoubleSummaryStatistics dss =employee.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
//		System.out.println(dss.getCount());
//		System.out.println(dss.getAverage());
//		System.out.println(dss.getMax());
//		System.out.println(dss.getMin());
//		System.out.println(dss.getSum());
		
	}
}


