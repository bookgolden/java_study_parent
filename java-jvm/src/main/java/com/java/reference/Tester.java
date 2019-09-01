package com.java.reference;

class Tester {
	public static void main(String[] args) {
		// 通过cache访问student
		AccessOneStudentFromCache("SX1504001");

		// 假设JVM某刻自动GC了
		System.gc();
		sleep();

		// 再次通过cache访问student
		AccessOneStudentFromCache("SX1504002");
	}

	static void AccessOneStudentFromCache(String studentNum) {
		StudentCache studentCache = StudentCache.getInstance();
		System.out.println("Now access student: " + studentCache.getCachedStudent(studentNum));
	}

	static void sleep() {
		try {
			Thread.currentThread().sleep(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}