package com.java.cn.v2;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class DirectMemoryOOM {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Field unsafeField = Unsafe.class.getDeclaredFields()[0];
		unsafeField.setAccessible(true);
		Unsafe unsafe = (Unsafe)unsafeField.get(null);
		while(true){
			unsafe.allocateMemory(1l);
		}
	}
}
