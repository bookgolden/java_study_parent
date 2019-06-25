package com.java.cn.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.java.cn.bean.Employee;

public class LambdaTest3 {
	static List<Employee> employee = Arrays.asList(
			new Employee("张三", 18, 9999.99),
			new Employee("李四", 38, 5555.99),
			new Employee("王五", 50, 6666.66),
			new Employee("赵六", 16, 3333.33),
			new Employee("田七", 8, 7777.77)
		);
	
	public static void test1() {
		Collections.sort(employee, (x, y) -> Integer.compare(x.getAge(), y.getAge()));
		employee.stream().forEach(System.out::println);
	}
	
	/**
	 * java8内置的四大核心函数式接口
	 * 
	 * Consumer<T> ：消费型接口
	 * 		void accept(T t)
	 * 
	 * Supplier<T> : 供给型接口
	 * 		T get();
	 * 
	 * Functioon<T, R> : 函数型接口
	 * 		R apply(T t);
	 * 
	 * Predicate<T> : 断言型接口
	 * 		boolean test(T t)
	 */
	//Consumer<T> ：消费型接口
	public static void consumerTest(){
//		consumerHaddler(10000, m -> System.out.println("下馆子吃饭花了 "+ m +"元"));
		consumerHaddler2(1000, () -> {
			System.out.println(10.123);
			return 10.12;
		});
	}
	
	public static void consumerHaddler(double money, Consumer<Double> con){
		con.accept(money);
	}
	
	public static void consumerHaddler2(double money, Supplier<Double> con){
		con.get();
	}
	
	/**
	 * Supplier<T> : 供给型接口
	 * 产生指定个数的整数，并放入集合中
	 */
	public static void supplierTest(){
		List<Integer> list = supplierHanddler(10, () -> (int)(Math.random() * 100));
		System.out.println(list.toString());
	}
	
	public static List<Integer> supplierHanddler(int num, Supplier<Integer> supplier){
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<num; i++){
			list.add(supplier.get());
		}
		return list;
	}
	
	/**
	 * Functioon<T, R> : 函数型接口
	 * 需求：用于处理字符串
	 */
	public static void functionTest(){
		String vs = strHanddler("hello world..", (str) -> str.trim().toUpperCase());
		System.out.println(vs);
	}
	
	public static String strHanddler(String str, Function<String, String> fun){
		return fun.apply(str);
	}
	
	
	/**
	 * Predicate<T> : 断言型接口
	 * 需求：将满足条件的字符串放入集合中
	 */
	public static void predicateTest(){
		List<String> list = Arrays.asList("bob","oracle","java");
		List vList = filterStr(list, x -> x.length()>3);
		System.out.println(vList.toString());
	}
	
	public static List<String> filterStr(List<String> list, Predicate<String> pre){
		List<String> strList = new ArrayList<>();
		for(String vs : list){
			if(pre.test(vs)){
				strList.add(vs);
			}
		}
		return strList;
	}
	
	/**
	 * 
	 * 方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用“方法引用” (可以理解为方法引用是Lambda表达式的另外一种表现形式) 
	 * 
	 * 主要有三种语法格式：
	 * 
	 * 对象::实例方法名
	 * 
	 * 类::静态方法名
	 * 
	 * 类::实例方法名
	 * 
	 * 注意:
	 * 1 Lambda体中调用方法的参数列表与返回值类型,要与函数式接口中抽象方法的函数列表和返回值类型保持一致
	 * 2   若Lambda参数列表中的第一参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method
	 * 
	 * 二、构造器引用：
	 * 格式：ClassName::new
	 * 注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致
	 * 
	 * 三、数组引用
	 * 格式： Type[]::new
	 * 
	 */
	public static void main(String[] args) {
//		consumerTest();
//		supplierTest();
//		functionTest();
//		predicateTest();
//		consumerTest();
	}
	
}
