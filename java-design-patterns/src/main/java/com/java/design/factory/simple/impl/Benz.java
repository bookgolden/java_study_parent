package com.java.design.factory.simple.impl;

import com.java.design.factory.simple.Car;

public class Benz implements Car {
	public void run() {
		System.out.println("Benz开始启动了。。。。。");
	}

	public void stop() {
		System.out.println("Benz停车了。。。。。");
	}
}