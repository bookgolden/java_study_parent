package com.java.design.factory.simple;

import com.java.design.factory.simple.impl.Ford;

public class Test {
	public static void main(String[] args) {
//		Car c = Factory.getCarInstance("Ford");
//		if (c != null) {
//			c.run();
//			c.stop();
//		} else {
//			System.out.println("造不了这种汽车。。。");
//		}
		
		Ford ford = ReflectFactory.createCar(Ford.class);
		ford.run();
	}
}