package com.java.cn.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.java.cn.bean.Person;

/**
 * 一、Stream的三个操作步骤 1、创建Stream 2、中间操作 3、终止操作（终端操作）
 * 
 * @author 1800101645
 *
 */
public class StreamTest1 {

	public static void main(String[] args) {
		// 1、通过Collection系列集合提供的Stream()或parallelStream
		List<String> list = new ArrayList<>();
		Stream<String> stream1 = list.stream();

		// 2、通过Arrays中的静态方法stream()获取数组流
		Person[] person = new Person[10];
		Stream<Person> stream2 = Arrays.stream(person);

		// 3、通过Stream类中的静态方法of()
		Stream<String> stream3 = Stream.of("aaa", "bbb", "ccc");
		
		//4、创建无限流
		//迭代
		Stream<Integer> stream4 = Stream.iterate(0, x -> x+2);
//		stream4.forEach(System.out::println);
//		stream4.limit(10).forEach(System.out::println);
		
		//生成
//		Stream.generate(() -> Math.random()).forEach(x -> System.out.println(x));
		Stream.generate(() -> Math.random()).limit(5).forEach(System.out::println);
	}
}
