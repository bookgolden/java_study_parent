package com.java.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;

public class AllReferenceDemo {

	public static void main(String[] args) throws InterruptedException {
//		softRef_Memory_Enough();
//		softRef_Memory_NotEnough();
//		softRef_Queue();
		weakReference();
//		myHashMap();
//		myWeakHashMap();
//		referenceQueue();
//		phantomReference();
	}
	
	public static void softRef_Memory_Enough() {
//		Object o1 = new Object();
		User o1 = new User("java", 10);
		SoftReference<Object> softReference = new SoftReference<>(o1);
		System.out.println(o1);
		System.out.println(softReference.get());
		
		o1 = null;
		System.gc();
		
		System.out.println(o1);
		System.out.println(softReference.get());
		
//		try {
//			List<String> list = new ArrayList<>();
//			
//			if(true) {
//				for(int i=1;i<100000000;i++) {
//					list.add(new Random().nextInt(1000000)+"");
//				}
//			}
//		}finally {
//			o1 = null;
//			System.gc();
//			
//			System.out.println(o1);
//			System.out.println(softReference.get());
//		}
	}
	
	/**
	 * JVM配置，故意产生大对象并配置小的内存，让它内存不够用导致OOM，看软引用的回收情况
	 * -Xms5m -Xmx5m -XX:+PrintGCDetails
	 * @throws InterruptedException 
	 */
	public static void softRef_Memory_NotEnough() throws InterruptedException {
		Object o1 = new Object();
		SoftReference<Object> softReference = new SoftReference<>(o1);
		System.out.println(o1);
		System.out.println(softReference.get());
		
		o1 = null;
		System.gc();
		
		System.out.println(o1);
		System.out.println(softReference.get());
		try {
			byte[] bytes = new byte[30 * 1024 * 1024];
		}catch(Throwable e) {
			e.printStackTrace();
		}finally {
			System.out.println(o1);
			System.out.println(softReference.get());
		}
	}
	
	public static void softRef_Queue() throws InterruptedException {
		User user = new User("bob", 30);
		ReferenceQueue<User> queue = new ReferenceQueue<>();
		ExtSoftReference extSoftReference = new ExtSoftReference(user, queue);

		System.out.println(user);
		System.out.println(extSoftReference.get());
		System.out.println("================");

		user = null;
		System.gc();
		Thread.sleep(500);

		System.out.println(user);
		System.out.println(extSoftReference.get());
		System.out.println("================");

		try {
			byte[] bytes = new byte[30 * 1024 * 1024];
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
		}

		ExtSoftReference extRef = null;
		while ((extRef = (ExtSoftReference) queue.poll()) != null) {
			System.out.println(extRef.getKey() + " , " + extRef.get());
		}
	}

	public static void weakReference() {
		Object o1 = new Object();
		WeakReference<Object> weakReference = new WeakReference<>(o1);
		System.out.println(o1);
		System.out.println(weakReference.get());
		
		o1 = null;
		System.gc();
		System.out.println("=====================");
		
		System.out.println(o1);
		System.out.println(weakReference.get());
		
		
//		Map<Object, Object> objectMap = new WeakHashMap<Object, Object>();
//		for (int i = 0; i <= 1000; i++) {
//		    objectMap.put(String.valueOf(i), new Object()); 
//			if (i % 10 == 1) {
//				System.gc();
//			}
//		    System.out.println("no:"+i+" size:" + objectMap.size() +" map = "+objectMap);
//		}
	}
	
	public static void myHashMap() {
		Integer key = new Integer(1);
		String val = "HasMap";
		HashMap<Integer, String> map = new HashMap<>();
		
		map.put(key, val);
		System.out.println(map);
		
		key = null;
		System.out.println(map);

		System.gc();
		System.out.println(map + "\t" + map.size());
	}
	
	public static void myWeakHashMap() {
		WeakHashMap<Integer, String> map = new WeakHashMap<>();
		Integer key = new Integer(1);
		String val = "HashMap";
		Integer k2 = new Integer(2);
		String v2 = "weakHashMap";

		map.put(key, val);
		map.put(k2, v2);
		System.out.println(map);

		key = null;
		System.out.println(map);

//		System.gc();
		System.out.println(map + "\t" + map.size());
	}

	public static void referenceQueue() throws InterruptedException {
//		Object o1 = new Object();
//		ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
//		WeakReference<Object> weakReference = new WeakReference<>(o1, referenceQueue);
		
//		System.out.println(o1);
//		System.out.println(weakReference.get());
//		System.out.println("poll = " + referenceQueue.poll());
//		System.out.println("remove = " + referenceQueue.remove());
		
		User o1 = new User("bob", 30);
		ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
		ExtWeakReference weakReference = new ExtWeakReference(o1, referenceQueue);
		
		System.out.println(o1);
		System.out.println(weakReference.get());
		
		System.out.println("===================");
		
		o1 = null;
		System.gc();
		Thread.sleep(500);
		
		System.out.println(o1);
		System.out.println(weakReference.get());
		ExtWeakReference extWeakReference = null;
		while((extWeakReference = (ExtWeakReference)referenceQueue.poll()) != null) {
			System.out.println(extWeakReference.getKey()+", "+ extWeakReference.get());
		}
	}
	
	public static void phantomReference() throws InterruptedException {
		Object o1 = new Object();
		ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
		PhantomReference<Object> phantomReference = new PhantomReference<>(o1, referenceQueue);
		
		System.out.println(o1);
		System.out.println(phantomReference.get());
		System.out.println(referenceQueue.poll());
		
		System.out.println("=====================");
		o1 = null;
		System.gc();
		Thread.sleep(500);
		
		System.out.println(o1);
		System.out.println(phantomReference.get());
		System.out.println(referenceQueue.poll());
	}
	
}
