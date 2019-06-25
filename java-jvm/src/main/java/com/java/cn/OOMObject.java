package com.java.cn;

import java.util.ArrayList;
import java.util.List;

public class OOMObject {

	public static void main(String[] args) throws InterruptedException {
		fillHeap(1000);
		System.gc();
	}

	public static void fillHeap(int num) throws InterruptedException {
		List<OOMObjects> list = new ArrayList<OOMObjects>();
		for (int i = 0; i < num; i++) {
			Thread.sleep(50);
			list.add(new OOMObjects());
		}
	}
}

class OOMObjects {
	public byte[] placeholder = new byte[64 * 1024];
}