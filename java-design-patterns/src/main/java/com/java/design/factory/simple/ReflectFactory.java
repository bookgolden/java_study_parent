package com.java.design.factory.simple;

public class ReflectFactory {

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static <T extends Car> T createCar(Class<T> clazz) {
		T result = null;
		try {
			result = (T) Class.forName(clazz.getName()).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

}
