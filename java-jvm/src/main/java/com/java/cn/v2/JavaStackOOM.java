package com.java.cn.v2;

public class JavaStackOOM {

	private void dontStop() {
		while (true) {

		}
	}
	private void dontStop2() {
		while (true) {
			
		}
	}

	public void stackLeakByThread() {
		while (true) {
			new Thread(new Runnable(){
				@Override
				public void run() {
					dontStop();
				}
			}).start();
			new Thread(new Runnable(){
				@Override
				public void run() {
					dontStop2();
				}
			}).start();
		}
	}

	public static void main(String[] args) {
		JavaStackOOM oom = new JavaStackOOM();
		oom.stackLeakByThread();
	}

}
