package com.java.cn.ann;

import java.lang.reflect.Method;

public class TestAnnotation {

	@MyAnnotation("Hello")
	@MyAnnotation("World")
	public void show() {

	}

	public void show(@MyAnnotation("abc") String str) {
	}

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Class<TestAnnotation> clazz = TestAnnotation.class;
		Method method = clazz.getMethod("show");
		MyAnnotation[] mas = method.getAnnotationsByType(MyAnnotation.class);
		for (MyAnnotation myAnnotation : mas) {
			System.out.println(myAnnotation.value());
		}
	}

}
