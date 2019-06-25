package com.java.cn.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.java.cn.bean.Employee;

public class StreamTest2 {
	static List<Employee> employee = Arrays.asList(
			new Employee("张三", 18, 9999.99),
			new Employee("李四", 38, 5555.99),
			new Employee("王五", 50, 6666.66),
			new Employee("赵六", 16, 3333.33),
			new Employee("田七", 8, 7777.77),
			new Employee("田七", 8, 7777.77),
			new Employee("田七", 8, 7777.77)
		);
	
	//中间操作
	/**
	 * 筛选与切片
	 * filter--接收Lambda,从流中排除某些元素
	 * limit--截断流，使其元素不超过给定数量
	 * skip(n)--跳过元素，返回一个扔掉了前n个元素的流，若流中不足n个，则返回一个空流，与limit(n)互补
	 * distinct--筛选，通过流所生成元素的hashCode()和equals()去除重复元素
	 */
	public static void main(String[] args) {
		
//		employee.stream().sorted((x, y)-> Integer.compare(x.getAge(), y.getAge())).forEach(System.out::println);
		
		//employee.stream().filter((x) -> x.getAge() > 35).forEach(System.out::println);
//		Stream<Employee> stream = employee.stream().filter((x) -> {
//			System.out.println("中间操作......");
//			return x.getAge() > 35;
//		});
//		stream.forEach(System.out::println);//内部迭代
//		System.out.println("------------------------------------");
//		//外部迭代
//		Iterator<Employee> iter = employee.iterator();
//		while(iter.hasNext()){
//			System.out.println(iter.next());
//		}
		
		//limit
//		employee.stream().filter((x) -> x.getSalary()>5000)
//						.limit(2)
//						.forEach(System.out::println);
		
		//skip
//		employee.stream().skip(2).forEach(System.out::println);
//		employee.stream().skip(2).limit(1).forEach(System.out::println);
		
		//distinct
//		employee.stream().skip(2).distinct().forEach(System.out::println);
		
		//========================================================================================
		
		/**
		 * 映射：
		 * map--接收Lambda,将元素转换成其他形式或提取信息，接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素，
		 * flatMap--接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
		 */
		
//		List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");
//		list.stream().map((str)->str.toUpperCase())
//		.forEach(System.out::println);
		
//		employee.stream()
//				.map(Employee::getName)
//				.forEach(System.out::println);
		
//		Arrays.asList("aaa","bbb","ccc","ddd","eee").stream()
//													.map((s)->filterCharacter(s))
//													.forEach(sm -> {
//														sm.forEach(System.out::print);
//													});
		
//		String str = Arrays.asList("aaa","bbb","ccc","ddd","eee").stream()
//													.collect(Collectors.joining(",", "=====", "==="));
//		System.out.println(str);
		
//		Arrays.asList("aaa","bbb","ccc","ddd","eee").stream()
//													.map(x->filterCharacter(x))
//													.forEach(System.out::print);
		
		//flatMap
//		Arrays.asList("aaa","bbb","ccc","ddd","eee").stream()
//													.flatMap(x -> filterCharacter(x))
//													.forEach(System.out::print);
		
//		//sorted
//		Arrays.asList("bbb","aaa","ddd","ccc","eee").stream()
//													.flatMap(x->filterCharacter(x))
//													.sorted().forEach(System.out::print);
		
		employee.stream()
				.sorted((x, y)-> {
//					return x.getAge()>y.getAge();
					return x.getName().compareTo(y.getName());
				}).forEach(System.out::println);
	}
	
	public static Stream<Character> filterCharacter(String str){
		List<Character> list = new ArrayList<>();
		for(Character c : str.toCharArray()){
			list.add(c);
		}
		return list.stream();
	}
}
