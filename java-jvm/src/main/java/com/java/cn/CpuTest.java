package com.java.cn;

public class CpuTest {

	public static void main(String[] args) throws InterruptedException {
		loop();
		endlessLoop();
	}

	public static void endlessLoop() throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					System.out.println("hello world! loop! --endlessLoop ---  ");
				}
			}
		}, "Thread_endlessLoop").start();
		
	}

	public static void loop() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100000000; i++) {
					System.out.println("hello world! endless loop! -- loop ----");
				}
			}
		}, "Thread_loop").start();
	}
}
