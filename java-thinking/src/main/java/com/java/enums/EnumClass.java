package com.java.enums;

//: enumerated/EnumClass.java
// Capabilities of the Enum class

enum Shrubbery {
	GROUND, CRAWLING, HANGING
}

public class EnumClass {
	public static void main(String[] args) {
		for (Shrubbery s : Shrubbery.values()) {
			System.out.println(s + " ordinal: " + s.ordinal());
			System.out.println(s.compareTo(Shrubbery.CRAWLING) + " ");
			System.out.println(s.equals(Shrubbery.CRAWLING) + " ");
			System.out.println(s == Shrubbery.CRAWLING);
			System.out.println(s.getDeclaringClass());
			System.out.println(s.name());
			System.out.println("----------------------");
		}

		System.out.println("Produce an enum value from a string name:");
		
		for (String s : "HANGING CRAWLING GROUND".split(" ")) {
			Shrubbery shrub = Enum.valueOf(Shrubbery.class, s);
			System.out.println(shrub);
		}
	}
}

/* Output:
GROUND ordinal: 0
-1 false false
class Shrubbery
GROUND
----------------------
CRAWLING ordinal: 1
0 true true
class Shrubbery
CRAWLING
----------------------
HANGING ordinal: 2
1 false false
class Shrubbery
HANGING
----------------------
HANGING
CRAWLING
GROUND
*///:~
