package com.java.queue;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo02 {

	static SynchronousQueue<String> queue = new SynchronousQueue<>();

	public static void main(String[] args) {
		new Thread(() -> {
			try {
				while (true) {
//					TimeUnit.SECONDS.sleep(1);
//					Thread.sleep(100);
					String vs = new Random().nextInt(1000) + " , " + System.currentTimeMillis();
					queue.put(vs);
					System.out.println(Thread.currentThread().getName() + " 放数据: " + vs);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "AA").start();

		new Thread(() -> {
			while (true) {
				try {
					System.out.println(Thread.currentThread().getName() + " 取数据：" + queue.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "BB").start();

		new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(100);
					System.out.println(Thread.currentThread().getName() + " 取数据：" + queue.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "CC").start();
	}

}
