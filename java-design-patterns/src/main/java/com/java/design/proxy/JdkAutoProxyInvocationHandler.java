package com.java.design.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.java.design.proxy.impl.RealSubject;

public class JdkAutoProxyInvocationHandler implements InvocationHandler {

	private JdkAutoProxy jdkAutoProxy;

	public JdkAutoProxyInvocationHandler(JdkAutoProxy jdkAutoProxy) {
		super();
		this.jdkAutoProxy = jdkAutoProxy;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String targetClassName = jdkAutoProxy.getClass().getName();
		System.out.println("代理类="+ proxy.getClass() + ", 目标对象类="+targetClassName+", 调用代理类 方法名= "+method.getName());
//		if (method.getName().equals("sellBooks")) {
//			int invoke = (int) method.invoke(jdkAutoProxy, args);
//			System.out.println("调用的是卖书的方法");
//			return invoke;
//		} else {
//			String string = (String) method.invoke(jdkAutoProxy, args);
//			System.out.println("调用的是说话的方法");
//			return string;
//		}
		int invoke = (int) method.invoke(jdkAutoProxy, args);
		System.out.println("jdk proxy end " + invoke + ", " + method.getName());
		return invoke;
	}


	public static void main(String[] args) {
		// 真实对象
		JdkAutoProxy realSubject = new RealSubject();
		JdkAutoProxyInvocationHandler myInvocationHandler = new JdkAutoProxyInvocationHandler(realSubject);
		// 代理对象
		JdkAutoProxy proxyClass = (JdkAutoProxy) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
				new Class[] { JdkAutoProxy.class }, myInvocationHandler);
		proxyClass.sellBooks();
//		proxyClass.speak();
	}
}
