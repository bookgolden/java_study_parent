package com.java.design.factory.method;

public class Test {
	public static void main(String[] args) {
		VehicleFactory factory = new PlaneFactory();
		Moveable m = factory.create();
		m.run();
	}
}