package com.java.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ExtWeakReference extends WeakReference<User> {

	private static String key;

//	public ExtWeakReference(T referent,  ReferenceQueue<? super Class<?>> queue) {
//		super(referent, queue);
//		this.key = key;
//	}

//	public ExtWeakReference(Class<?> referent,  ReferenceQueue<? super Class<?>> queue) {
//		super(referent, queue);
//	}

	public ExtWeakReference(User user, ReferenceQueue<? super User> queue) {
		super(user, queue);
		this.key = user.getName();
	}

	public static String getKey() {
		return key;
	}

}
