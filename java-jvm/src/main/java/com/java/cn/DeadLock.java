package com.java.cn;

public class DeadLock {
	public static void main(String[] args) {
		for (int i = 0; i < 10000; i++) {
			new Thread(new SynAddRunnable(1, 2), "Thread_java").start();
			new Thread(new SynAddRunnable(2, 1), "Thread_oracle").start();
		}
	}
}

class SynAddRunnable implements Runnable {

	int a, b;

	public SynAddRunnable(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	@Override
	public void run() {
		synchronized (Integer.valueOf(a)) {
			synchronized (Integer.valueOf(b)) {
				System.out.println(a + b);
			}
		}
	}

}