package com.java.cn;

import java.util.*;
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


	/**
	 * 从数据中心获取指定表的各数据信息
	 * @param tables 表名
	 * @return
	 */
//	public <T, R> List<R> getSourceData(T table) {
//		return (List<R)new Object();
//	}
//
//	/**
//	 * @param tables 要获取数据的表名集合
//	 * @return
//	 */
//	public Map<String, List<Object>> getDataFromCenter(String... tables) {
//
//		Map<String, List<Object>> map = new HashMap<>();
//		String[] tableNames = new String[]{"user", "product"};
//		for (String tableName : tableNames) {
//			List<Object> list = (List<Object>) getSourceData(tableNames);
//			map.put(tableName, list);
//		}
//		return map;
//	}

}
