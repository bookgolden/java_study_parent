package com.java.enums.menu;

import com.java.util.Enums;

public enum Meal2 {
	APPETIZER(Food.Appetizer.class),
	MAINCOURSE(Food.MainCourse.class),
	DESSERT(Food.Dessert.class),
	COFFEE(Food.Coffee.class);
	private Food[] values;

	private Meal2(Class<? extends Food> kind) {
		values = kind.getEnumConstants();
		for (Food food : values){
			Food[] enumConstants = food.getClass().getEnumConstants();
			for (Food f : enumConstants){
				if(f instanceof Food.Appetizer){
					System.out.println("ok");
					Food.Appetizer ap = (Food.Appetizer)f;
					System.out.println(ap.name()+" , "+ap.getNum());
				}
			}
		}
		System.out.println("enum construct init : "+ values);
	}

	public interface Food {
		enum Appetizer implements Food {
			SALAD(10), SOUP(20), SPRING_ROLLS(30);
			private int num;

			Appetizer(int num) {
				this.num = num;
			}

			public int getNum() {
				return num;
			}

			public void setNum(int num) {
				this.num = num;
			}
		}

		enum MainCourse implements Food {
			LASAGNE, BURRITO, PAD_THAI, LENTILS, HUMMOUS, VINDALOO;
		}

		enum Dessert implements Food {
			TIRAMISU, GELATO, BLACK_FOREST_CAKE, FRUIT, CREME_CARAMEL;
		}

		enum Coffee implements Food {
			BLACK_COFFEE, DECAF_COFFEE, ESPRESSO, LATTE, CAPPUCCINO, TEA, HERB_TEA;
		}
	}

	public Food randomSelection() {
		return Enums.random(values);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			Meal2[] meal2 = Meal2.values();
			for (Meal2 meal : Meal2.values()) {
				Food food = meal.randomSelection();
				System.out.println("===="+meal +" -> "+food);
			}
			System.out.println("---");
		}
	}
}

/* Same output as Meal.java *///:~
