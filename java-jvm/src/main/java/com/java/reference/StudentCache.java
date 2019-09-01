package com.java.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;

public class StudentCache {

	/* for GC trace */
	private ReferenceQueue<Student> studentReferenceQueue;
	/* for student cache */
	private HashMap<String, StudentReference> studentCacheHashMap;
	
	public StudentCache() {
		studentReferenceQueue = new ReferenceQueue<Student>();
		studentCacheHashMap = new HashMap<String, StudentReference>();
	}

	/* Singleton */
	public static StudentCache getInstance() {
		return InnerClassStudentCache._INSTANCE;
	}

	private static class InnerClassStudentCache {
		public static final StudentCache _INSTANCE = new StudentCache();
	}

	/* Cache Interface */
	public Student getCachedStudent(String studentNumber) {
		cleanGCedCache();
		// 不存在该Student缓存
		if (!studentCacheHashMap.containsKey(studentNumber)) {
			// 构造Student实例
			/* 从数据库中读取该student信息，然后构造Student。此处为了方便，使用测试类StudentDataSource作为辅助 */
			Student stu = StudentDataSource.getStudent(studentNumber);
			if (null == stu) {
				return null;
			}
			Student student = new Student(stu.getStudentNumber(), stu.getName(), stu.getAge());
			// 通过Reference加入缓存
			StudentReference studentReference = new StudentReference(student, studentReferenceQueue);
			studentCacheHashMap.put(student.getStudentNumber(), studentReference);
		}
		// 从缓存中获取StudentReference，并获取Student强引用作为返回值
		return studentCacheHashMap.get(studentNumber).get();
	}

	/* clean cached students which is GCed from hashMap */
	private static class StudentReference extends SoftReference<Student> {
		/* 在GC回收Student之后，此Reference对象被放入ReferenceQueue，加标识以识别是哪个student对象被回收 */
		public final String studentId;
		
		public StudentReference(Student referent, ReferenceQueue<? super Student> q) {
			super(referent, q);
			this.studentId = referent.getStudentNumber();
		}
	}

	private void cleanGCedCache() {
		StudentReference studentReference = null;
		while ((studentReference = (StudentReference) studentReferenceQueue.poll()) != null) {
			// 将已回收的Student对象从cache中移除
			studentCacheHashMap.remove(studentReference.studentId);
			System.out.println("student " + studentReference.studentId + " has been GCed, and found in referenceQueue.");
		}
	}

	public void destroy() {
		// 清除Cache
		cleanGCedCache();
		studentCacheHashMap.clear();
		System.gc();
		System.runFinalization();
	}
}