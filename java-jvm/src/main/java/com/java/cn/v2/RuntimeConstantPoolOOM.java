package com.java.cn.v2;

public class RuntimeConstantPoolOOM {

//	public static void main(String[] args) {
//		List<String> list = new ArrayList<String>();
//		int i = 0;
//		while (true) {
//			list.add(String.valueOf(i++).intern());
//		}
//	}
	
//	public static void main(String[] args) {
//		String s2 = new StringBuilder("ja").append("va").toString();
//		System.out.println(s2.intern() == s2);
//		
//		String s1 = new StringBuilder("计算机").append("软件").toString();
//		System.out.println(s1.intern() == s1);
//	}
	
	private Object obj;
	
	public static void main(String[] args) {
		RuntimeConstantPoolOOM m1 = new RuntimeConstantPoolOOM();
		RuntimeConstantPoolOOM m2 = new RuntimeConstantPoolOOM();
		m1.obj = m1;
		m2.obj = m2;
		
		m1 = null;
		m2 = null;
		System.gc();
	}
}
