package com.java.design.proxy.impl;

import com.java.design.proxy.JdkAutoProxy;

public class RealSubject implements JdkAutoProxy {
	@Override
	public int sellBooks() {
		System.out.println("目标方法, 卖书");
		return 1;
	}
//	@Override
//	public String speak() {
//		System.out.println("说话");
//		return "张三";
//	}
}