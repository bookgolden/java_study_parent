package com.java.design.factory.method;

import com.java.design.factory.method.impl.Plane;

public class PlaneFactory extends VehicleFactory {
	public Moveable create() {
		return new Plane();
	}
}