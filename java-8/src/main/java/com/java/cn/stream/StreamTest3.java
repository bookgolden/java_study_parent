package com.java.cn.stream;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import com.java.cn.bean.Employee;
import com.java.cn.bean.Employee.Status;

public class StreamTest3 {
	
	static List<Employee> employee = Arrays.asList(
			new Employee("张三", 18, 9999.99, Status.FREE),
			new Employee("李四", 38, 5555.99, Status.BUSY),
			new Employee("王五", 50, 6666.66, Status.VOCATION),
			new Employee("赵六", 16, 3333.33, Status.FREE),
			new Employee("麻五", 116, 30.33, Status.FREE),
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
//		boolean b2 = employee.stream().anyMatch((e) -> e.getStatus().equals(Status.BUSY));
//		boolean b3 = employee.stream().noneMatch((e) -> e.getStatus().equals(Status.BUSY));
//		System.out.println("b1=" + b1 + ", b2=" + b2 + ", b3=" + b3);
		
//		Optional<Employee> op1 = employee.stream().sorted((x, y) -> Double.compare(x.getSalary(), x.getSalary())).findFirst();
//		Optional<Employee> op2 = employee.stream().filter((e)->e.getStatus().equals(Status.FREE)).findAny();
//		Optional<Employee> op3 = employee.parallelStream().filter((e)->e.getStatus().equals(Status.FREE)).findAny();
//		System.out.println(op1.get() + " \n" + op2.get() + " \n" + op3.get());
		
//		long count = employee.parallelStream().count();
//		System.out.println(count);

//		Optional<Employee> maxEmp = employee.stream().max((x, y) -> Double.compare(x.getSalary(), y.getSalary()));
//		Optional<Employee> minEmp = employee.stream().max((x, y) -> Double.compare(x.getSalary(), y.getSalary()));
//		System.out.println(maxEmp.get() + "\n" + minEmp.get());
		
//		Optional<Double> max = employee.stream().map((x)->x.getSalary()).max((x, y)-> Double.compare(x, y));
//		Optional<Double> min = employee.stream().map(Employee::getSalary).min((x, y)-> Double.compare(x, y));
//		System.out.println(max.get() + "\n" + min.get());
		
		/**
		 * 归约
		 * reduce(T identity, BinaryOperator)
		 * reduce(BinaryOperator)--可以将流中元素反复结合起来，得到一个值
		 */
//		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
//		Integer sum = list.stream().reduce(0, (x, y)-> x + y);
//		System.out.println(sum);
		
//		Optional<Double> salarySum = employee.stream().map(Employee::getSalary).reduce(Double::sum);
//		System.out.println(salarySum.get());
		
		/**
		 * 收集
		 * collect--将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
		 */
//		employee.stream().map(Employee::getName).collect(Collectors.toList()).forEach(System.out::print);
		
//		String vs = employee.stream().map(Employee::getName).collect(Collectors.joining(",", "===", "==="));
		String vs = employee.stream().map(Employee::getName).collect(Collectors.joining(","));
		System.out.println(vs);
		
//		employee.stream().map(Employee::getName).collect(Collectors.toSet()).forEach(System.out::println);
//		employee.stream().collect(Collectors.toSet()).forEach(System.out::println);
		
//		HashSet<String> hashSet = employee.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
//		System.out.println(hashSet.toString());
//		hashSet.forEach(System.out::print);
		
//		Map<Integer, Employee> ep = employee.stream().collect(Collectors.toMap(Employee::getAge, Function.identity()));
//		System.out.println(ep);
		
		//总数
//		Long count = employee.stream().collect(Collectors.counting());
//		Long cound = employee.stream().count();
//		System.out.println(count + ", " + cound);
		
		//平均值
//		Double avg = employee.stream().collect(Collectors.averagingDouble(Employee::getSalary));
//		System.out.println(avg);
		
		//总和
//		double sum = (double) employee.stream().collect(Collectors.summingDouble(Employee::getSalary));
//		System.out.println(sum);
		
		//最大值
//		Optional<Employee> emp = employee.stream().collect(Collectors.maxBy((x, y)-> Double.compare(x.getSalary(), y.getSalary())));
//		Optional<Employee> emp = employee.stream().max((x, y) -> Double.compare(x.getSalary(), y.getSalary()));
//		System.out.println(emp.get());
		
		//最小值
//		Optional<Double> min = employee.stream().map(Employee::getSalary).collect(Collectors.minBy(Double::compare));
//		System.out.println(min.get());
		
		//分组
//		Map<Status, List<Employee>> map = employee.stream().collect(Collectors.groupingBy(Employee::getStatus));
//		System.out.println(map);
//		map.forEach((k, v) -> System.out.println(k + " = " + v));
		
		//多级分组
//		Map<Status, Map<String, List<Employee>>> map = employee.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e->{
//			if(((Employee)e).getAge()<25){
//				return "青年";
//			}else{
//				return "中老年";
//			}
//		})));
//		System.out.println(map);
		
		//分区
//		Map<Boolean,List<Employee>> map = employee.stream().collect(Collectors.partitioningBy((e)-> e.getSalary()>8000));
//		System.out.println(map.get(true));
		
		//综合统计
//		DoubleSummaryStatistics ds = employee.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
//		System.out.println("count="+ds.getCount()+", sum="+ds.getSum()+", average="+ds.getAverage()+", max="+ds.getMax()+", min="+ds.getMin());
//		System.out.printf("count=%s, sum=%s, average=%s, max=%s, min=%s", ds.getCount(), ds.getSum(), ds.getAverage(), ds.getMax(), ds.getMin());
		
//		List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
//		IntSummaryStatistics is = primes.stream().mapToInt((x) -> x).summaryStatistics();
//		System.out.printf("count=%s, sum=%s, average=%s, max=%s, min=%s", is.getCount(), is.getSum(), is.getAverage(), is.getMax(), is.getMin());
		
	}
}


