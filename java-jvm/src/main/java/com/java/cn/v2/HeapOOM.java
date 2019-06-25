package com.java.cn.v2;

public class HeapOOM {

	private static final int _1MB = 1024 * 1024;
	
	public HeapOOM() {
		super();
	}

	public static void main(String[] args) {
//		List<HeapOOM> list = new ArrayList<HeapOOM>();
//		while (true) {
//			list.add(new HeapOOM());
//		}
//		testAllocation();
//		testPretenureSizeThreshold();
		testTenuringThreshold();
	}

//	public static void testAllocation() {
//		byte[] a1, a2, a3, a4;
//		a1 = new byte[2 * _1MB];
//		a2 = new byte[2 * _1MB];
//		a3 = new byte[2 * _1MB];
//		a4 = new byte[4 * _1MB];
//	}

	/**
	 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
	 */
//	public static void testPretenureSizeThreshold() {
//		byte[] a1;
//		a1 = new byte[4 * _1MB];
//	}
	
	/**
	 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
	 */
	public static void testTenuringThreshold() {
		byte[] a1, a2, a3;
		a1 = new byte[_1MB / 4];
		a2 = new byte[4 * _1MB];
		a3 = new byte[4 * _1MB];
		a3 = null;
		a3 = new byte[4 * _1MB];
	}
	
}
