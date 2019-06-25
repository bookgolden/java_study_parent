package com.java.cn.v2;

public class StackTest {

	private int stackLength = 1;

	public void stackLeak() {
		stackLength++;
		
		stackLeak();
	}

	public static void main(String[] args) {
		StackTest test = new StackTest();
		try {
			test.stackLeak();
		} catch (Throwable e) {
			System.out.println("stack length:" + test.stackLength);
			
			throw e;
		}
	}

}
