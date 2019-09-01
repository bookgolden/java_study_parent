package com.java.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class QueueBasicTest {

	public static void main(String[] args) throws InterruptedException {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);
		// 1、
//		queue.add("123");
//		queue.add("456");
//		queue.add("456");

		// 2、
//		boolean flag = queue.offer("123");
//		System.out.println(flag);
//		
//		flag = queue.offer("456");
//		System.out.println(flag);
//		
//		flag = queue.offer("456");
//		System.out.println(flag);

		// 3、
//		queue.put("123");
//		queue.put("456");
//		queue.put("456");

		// 4、
		boolean flag = queue.offer("123", 2, TimeUnit.SECONDS);
		System.out.println(flag);
		flag = queue.offer("456", 2, TimeUnit.SECONDS);
		System.out.println(flag);
		flag = queue.offer("456", 2, TimeUnit.SECONDS);
		System.out.println(flag);

		System.out.println("======所有元素=====" + queue.toString() + " , size=" + queue.size());

		while (!queue.isEmpty()) {
			// 1、 2、
//			System.out.println("去除 = "+queue.remove() + ", 剩余  = " + queue.toString());
			// 3、
//			System.out.println(" take = " + queue.take()+", 剩余 = "+ queue.toString());
			// 4、
			System.out.println("去除 = " + queue.poll(2, TimeUnit.SECONDS) + ", 剩余  = " + queue.toString());
		}
	}
}
