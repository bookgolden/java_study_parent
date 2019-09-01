package com.java.reference;

import java.util.HashMap;

public class StudentDataSource {
	static HashMap<String, Student> students;
	static {
		students = new HashMap<String, Student>();
		students.put("SX1504001", new Student("SX1504001", "ZhangSan", 25));
		students.put("SX1504002", new Student("SX1504002", "LiSi", 25));
		students.put("SX1504003", new Student("SX1504003", "WangWu", 25));
		students.put("SX1504004", new Student("SX1504004", "LiuLiu", 25));
		students.put("SX1504005", new Student("SX1504005", "AAAAAA", 25));
		students.put("SX1504006", new Student("SX1504006", "BBBBBB", 25));
		students.put("SX1504007", new Student("SX1504007", "CCCCCC", 25));
		students.put("SX1504008", new Student("SX1504008", "DDDDDD", 25));
		students.put("SX1504009", new Student("SX1504009", "EEEEEE", 25));
		students.put("SX1504010", new Student("SX1504010", "FFFFFF", 25));
		// .....
	}

	static Student getStudent(String studentNum) {
		return students.get(studentNum);
	}
}