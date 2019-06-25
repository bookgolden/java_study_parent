package com.java.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Test {

	public static void main(String[] args) {
		Runnable run = new Runnable() {
			@Override
			public void run() {
			}
		};

		Runnable run2 = () -> System.out.println("hello");
		run2.run();

		Consumer<String> con = x -> System.out.println(x);
		con.accept("hehe..");

		Comparator<Integer> comparator = (x, y) -> {
			return Integer.compare(x, y);
		};
		Collections.sort(Arrays.asList(1, 2, 3), comparator);

		happy(10d, (d) -> System.out.println(d));
		List<Integer> list = getNumList(10, () -> (int) (Math.random() * 100));
		for (Integer vs : list) {
			System.out.print(vs + ", ");
		}
		String str1 = strHandler(" hello world...", (s1) -> s1.trim());
		System.out.println();
		System.out.println(str1);
	}

	public static void happy(double money, Consumer<Double> con) {
		con.accept(money);
	}

	public static List<Integer> getNumList(int num, Supplier<Integer> sup) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			list.add(sup.get());
		}
		return list;
	}

	public static String strHandler(String str, Function<String, String> fun) {
		return fun.apply(str);
	}
}
