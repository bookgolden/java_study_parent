package com.java.T;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.java.reflect.Employee;

public class Test {
	public static void main(String[] args) {
//		List arrayList = new ArrayList();
//		arrayList.add("aaaa");
////		arrayList.add(100);
//
//		for (int i = 0; i < arrayList.size(); i++) {
//			String item = (String) arrayList.get(i);
//			System.out.println("泛型测试item = " + item);
//		}

		List<String> stringArrayList = new ArrayList<String>();
		List<Integer> integerArrayList = new ArrayList<Integer>();

		Class classStringArrayList = stringArrayList.getClass();
		Class classIntegerArrayList = integerArrayList.getClass();

		if (classStringArrayList.equals(classIntegerArrayList)) {
			System.out.println("泛型测试, 类型相同");
		}
		if(classIntegerArrayList == classStringArrayList) {
			System.out.println("oooooooooooooo");
		}
	}
	
	public static void eachCfg(Class Initclass, String taskType) {
		Field[] fields = Initclass.getDeclaredFields(); // 获取属性
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.getType().toString().endsWith("java.lang.String") && Modifier.isStatic(field.getModifiers())) {
					if (field.get(Employee.class) == null) {
						String attrname = field.getName();
						attrname = attrname.toLowerCase();
						Object[] paras = { attrname, taskType };
						// 调用getDeclaredMethod方法时
						// 参数1：调用改类的方法名称
						// 参数2：参数列表1中的参数类型
						// 参数3：参数列表中2的参数类型
						// getSimpleName 方法获得不带路径的类名称
						Method method = Initclass.getDeclaredMethod("check"+Initclass.getSimpleName(),String.class,String.class);
						// invoke方法
						// 参数1：类的实例方法
						// 参数2：调用上面的方法的参数值（注意顺序）
						method.invoke(Initclass.newInstance(), paras);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
