package com.java.design.factory.simple;

import com.java.design.factory.simple.impl.Benz;
import com.java.design.factory.simple.impl.Ford;

public class Factory {
	public static Car getCarInstance(String type) {
		Car c = null;
		if ("Benz".equals(type)) {
			c = new Benz();
		}
		if ("Ford".equals(type)) {
			c = new Ford();
		}
		return c;
	}
	
	
}