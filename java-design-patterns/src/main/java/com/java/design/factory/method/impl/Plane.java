package com.java.design.factory.method.impl;

import com.java.design.factory.method.Moveable;

public class Plane implements Moveable {
	@Override
	public void run() {
		System.out.println("plane....");
	}
}