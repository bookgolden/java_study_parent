package com.java.enums;

// No values() method if you upcast an enum

enum Search {
	HITHER, YON
}

public class UpcastEnum {
	public static void main(String[] args) {
		Search[] vals = Search.values();
		Enum e = Search.HITHER; // Upcast
		// e.values(); // No values() in Enum
		for (Enum en : e.getClass().getEnumConstants()) {
			System.out.println(en);
		}
		
		System.out.println("===================");
		Class<Integer> intClass = Integer.class;
		for(Object obj : intClass.getEnumConstants()) {
			System.out.println(obj);
		}
	}
}

/* Output:
HITHER
YON
*///:~
