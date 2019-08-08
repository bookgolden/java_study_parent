package com.java.cn.lambda;

import java.util.Comparator;
import java.util.function.Consumer;

import com.java.cn.interfaces.MyFun;

public class LambdaTest2 {
	public static void LambdaGrammer() {
		//语法格式一：Lambda无参
		Runnable run = () -> System.out.println("Hello Lambda");
		run.run();
		
//		//语法格式二：Lambda一个参数，无返回值
		Consumer<String> con = t -> System.out.println(t);
		con.accept("Lambda一个参数，无返回值");
//		
//		//语法格式三：Lambda一个参数,小括号可以不写
//		Consumer<String> con2 = t -> System.out.println(t);
//		con.accept("Lambda一个参数，无返回值");
//		
//		//语法格式四：有两个及以上的参数，且方法体中有多条语句,则Lambda体则需要用{}
		Comparator<Integer> com = (x, y) -> {
			System.out.println("x=" + x + ", y=" + y);
			return Integer.compare(x, y);
		};
		
//		//语法格式五：若Lambda体中只有一条语句，则return和大括号可以不写
//		Comparator<Integer> com3 = (x, y) -> Integer.compare(x, y);
//	
//		//语法格式六：Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器可以通过上下文推断出数据类型,即：“类型推断”
//		Comparator<Integer> com4 = (Integer x, Integer y) -> Integer.compare(x, y);
//		Comparator<Integer> com5 = (x,  y) -> Integer.compare(x, y);
	}
	
	//需求：对一个数进行运算
	public static void test1(){
		Integer num = operation(10, (x) -> x * x);
		System.out.println(num);
		System.out.println("--------------");
		Integer num2 = operation(5, x -> x * 10);
		System.out.println(num2);
	}
	public static Integer operation(Integer num, MyFun mf){
		return mf.cal(num);
	}
	
	public static void main(String[] args) {
//		LambdaGrammer();
		test1();
//		test2();
//		test3();
	}
}
