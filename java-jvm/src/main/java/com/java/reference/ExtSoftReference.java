package com.java.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

public class ExtSoftReference extends SoftReference<User> {

	private String key;
	
	public ExtSoftReference(User user, ReferenceQueue<? super User> q) {
		super(user, q);
		this.key = user.getName(); 
	}

	public String getKey() {
		return key;
	}

}
