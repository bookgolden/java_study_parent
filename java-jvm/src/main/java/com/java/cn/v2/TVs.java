package com.java.cn.v2;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TVs {

	private static int v1 = 1;

	public TVs() {
//		System.out.println(v1);
	}

	static {
		int a = 2;
//		System.out.println(a);
	}

	public static void main(String[] args) {
		// new TVs();
//		String str = "bob";
//		List<String> list = Collections.singletonList("java");
//		System.out.println(list);
		
		Set<String> sen = Collections.singleton("mldn");
//		sen.add("oracle");
		System.out.println(sen);
		
//		List vlist = Collections.EMPTY_LIST;
//		Map map = Collections.EMPTY_MAP;
//		Set set = Collections.EMPTY_SET;
	}

}
