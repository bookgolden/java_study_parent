package com.java.reference;

class Student {
	/* Fields */
	private String studentNumber;
	private String name;
	private int age;

	public String getStudentNumber() {
		return studentNumber;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public Student(String studentNumber, String name, int age) {
		this.studentNumber = studentNumber;
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student{" + "studentNumber='" + studentNumber + '\'' + ", name='" + name + '\'' + ", age=" + age + '}';
	}
}