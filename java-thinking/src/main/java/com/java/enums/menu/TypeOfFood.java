package com.java.enums.menu;

import com.java.enums.menu.Food.Appetizer;
import com.java.enums.menu.Food.Coffee;
import com.java.enums.menu.Food.Dessert;
import com.java.enums.menu.Food.MainCourse;

public class TypeOfFood {
	public static void main(String[] args) {
		Food food = Appetizer.SALAD;
		food = MainCourse.LASAGNE;
		food = Dessert.GELATO;
		food = Coffee.CAPPUCCINO;
	}
}
