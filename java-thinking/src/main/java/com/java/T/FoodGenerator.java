package com.java.T;

import java.util.Random;

public class FoodGenerator implements Generator<String> {

	private String[] fruits = new String[] { "Apple", "Banana", "Pear" };

	@Override
	public String next() {
		Random rand = new Random();
		return fruits[rand.nextInt(3)];
	}

}
