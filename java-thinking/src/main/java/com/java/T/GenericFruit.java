package com.java.T;

import java.util.ArrayList;
import java.util.List;

public class GenericFruit {
//	class Fruit {
//		public String toString() {
//			return "fruit";
//		}
//	}

//	class Apple extends Fruit {
//		@Override
//		public String toString() {
//			return "apple";
//		}
//	}

//	class Person {
//		public String toString() {
//			return "Person";
//		}
//	}

//	class GenerateTest<T> {
//		public void show_1(T t) {
//			System.out.println(t.toString());
//		}
//
//		// 在泛型类中声明了一个泛型方法，使用泛型E，这种泛型E可以为任意类型。可以类型与T相同，也可以不同。
//		// 由于泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中并未声明泛型，编译器也能够正确识别泛型方法中识别的泛型。
//		public <E> void show_3(E t) {
//			System.out.println(t.toString());
//		}
//
//		// 在泛型类中声明了一个泛型方法，使用泛型T，注意这个T是一种全新的类型，可以与泛型类中声明的T不是同一种类型。
//		public <T> void show_2(T t) {
//			System.out.println(t.toString());
//		}
//	}
	public static void showKeyValue1(Generic<? extends Number> obj) {
		System.out.println("泛型测试, key value is " + obj.getKey());
	}

	public <T extends Number> T showKeyName(Generic<T> container){
	    System.out.println("container key :" + container.getKey());
	    T test = container.getKey();
	    return test;
	}
	
	public static void main(String[] args) {
		Generic<String> generic1 = new Generic<String>("11111");
		Generic<Integer> generic2 = new Generic<Integer>(2222);
		Generic<Float> generic3 = new Generic<Float>(2.4f);
		Generic<Double> generic4 = new Generic<Double>(2.56);

		//这一行代码编译器会提示错误，因为String类型并不是Number类型的子类
		//showKeyValue1(generic1);

		showKeyValue1(generic2);
		showKeyValue1(generic3);
		showKeyValue1(generic4);
		
//		Apple apple = new Apple();
//		Person person = new Person();
//
//		GenerateTest<Fruit> generateTest = new GenerateTest<Fruit>();
//		// apple是Fruit的子类，所以这里可以
//		generateTest.show_1(apple);
//		// 编译器会报错，因为泛型类型实参指定的是Fruit，而传入的实参类是Person
//		// generateTest.show_1(person);
//
//		// 使用这两个方法都可以成功
//		generateTest.show_2(apple);
//		generateTest.show_2(person);
//
//		// 使用这两个方法也都可以成功
//		generateTest.show_3(apple);
//		generateTest.show_3(person);
		
		
	}
}