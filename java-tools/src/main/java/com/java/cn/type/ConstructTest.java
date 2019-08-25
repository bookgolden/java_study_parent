package com.java.cn.type;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ConstructTest {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
//		constTest();
//		instanceTest();
//		constParamTest();
//		fieldTest();
//		methodTest();
		
		String[] str = new String[] {"123", "abc", "xo"};
//		List<String> list = new ArrayList<String>();
//		list.add("XXX");
//		list.add("CCC");
//		list.add("DDDD");
//		Class<?>[] cl = new Class<?>[] {Person.class};
		Class<?>[] cl;
//		testvo(str);

		
//		Class<?>[] paramTypes = new Class<?>[] {Integer.class, String.class};
//		Object[] vals = new Object[] {new Integer(10), new String("bob")};
		Class<?>[] paramTypes = new Class<?>[] {Integer.class, String.class, COLOR.class};
		Object[] vals = new Object[] {new Integer(10), "XIAOBO", COLOR.BLACK};
		
		Object obj = commonMethod("hello", Person.class, paramTypes, vals);
		
		if (obj instanceof Integer) {
			System.out.println("Integer = " + (Integer) obj);
		} else if (obj instanceof String) {
			System.out.println("String = " + (String) obj);
		} else if(obj instanceof Person) {
			Person p = (Person)obj;
			System.out.println(p);
		}
	}
	
	public static void constTest() throws NoSuchMethodException, SecurityException {
		// 1、枚举
		Class<?> clazz = Person.class;
		Constructor<?>[] constructors = clazz.getDeclaredConstructors();
		for (Constructor item : constructors) {
			System.out.println("枚举到的构造函数：" + item.toString());
		}

		System.out.println("===================");
		
		// 2、根据类型，获取指定的构造的构造函数
		Constructor<?> constructor = clazz.getDeclaredConstructor(Integer.class, String.class);
		System.out.println("指定参数得到的构造函数：" + constructor.toString());

		System.out.println("=================================================");
		
		// 1、枚举
		Class<?> clazzz = Person.class;
		Constructor<?>[] constructorss = clazzz.getConstructors();
		for (Constructor item : constructorss) {
			System.out.println("枚举到的构造函数：" + item.toString());
		}
		
		System.out.println("===================");
		
		// 2、根据类型，获取指定的构造的构造函数
		Constructor<?> constructorr = clazz.getConstructor(Integer.class, String.class);
		System.out.println("指定参数得到的构造函数：" + constructorr.toString());
	}
	
	public static void instanceTest() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<?> clazz = Person.class;
		Constructor<?> constructor = clazz.getDeclaredConstructor(Integer.class, String.class);
		constructor.setAccessible(true);
		 
		//构造实例一
		Person person1 = (Person) constructor.newInstance(new Integer(30),new String("harvic"));
		System.out.println("构造的参数为：" + person1.getName() + "  " + person1.getAge());
		 
		//构造实例二
		Person person2 = (Person) constructor.newInstance(50,"qijian");
		System.out.println("构造的参数为："+person2.getName() + "  "+ person2.getAge());
		 
		//构造实例三
		Person person3 = (Person) constructor.newInstance();
		person3.setAge(30);
		person3.setName("qijian");
		System.out.println("构造的参数为："+person3.getName() + "  "+ person3.getAge());
	}

	public static void constParamTest() {
		Class<?> clazz = Person.class;
		Constructor<?>[] constructors = clazz.getDeclaredConstructors();
		for (Constructor<?> c : constructors) {
			c.setAccessible(true);
			Class<?>[] types = c.getParameterTypes();
			StringBuilder builder = new StringBuilder("获取参数类型为：");
			for (Class t : types) {
				builder.append(t.getName());
				builder.append("   ");
			}
			Class<?> declarClazz = c.getDeclaringClass();
			
			System.out.println("访问修饰符 "+Modifier.toString(c.getModifiers()) + ", " +builder.toString() + ", 得到声明Constructor的类的Class对象:" + declarClazz);

		} 
	}

	public static void fieldTest() throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		Class<?> clazz = Person.class;
//		Field[] fields = clazz.getDeclaredFields();
//		for (Field field : fields) {
//			field.setAccessible(true);
//			Class<?> type = field.getType();
//			System.out.println("枚举到的field:" + type.getName() + "  " + field.getName());
//		}

//		Field field = clazz.getDeclaredField("age");
//		field.setAccessible(true);
//		Class<?> type = field.getType();
//		System.out.println("得到age对应的field:" + type.getName() + "  " + field.getName());
		
		Constructor<?> constructor = clazz.getConstructor();
		Person person = (Person)constructor.newInstance();
		 
		Field fName = clazz.getDeclaredField("name");
		fName.setAccessible(true);
		fName.set(person, "qijian");
		String val = (String)fName.get(person);
		System.out.println("fieldName:" + val + "   personName:" + person.getName());
		
	}

	public static void methodTest() throws NoSuchMethodException, SecurityException {
		Person person = new Person();
		Class<?> clazz = Person.class;
		
//		Method[] methods = clazz.getDeclaredMethods();
//		for (Method m : methods) {
//			System.out.println("枚举到的方法：" + m.toString());
//		}
		
		Method method = clazz.getDeclaredMethod("setName",String.class);
		System.out.println("得到指定方法："+method.toString());
	}
	
	public static void testvo(String... args) {
		for(String v : args) {
			System.out.println(v);
		}
	}
	
	static <R, T> R commonMethod(String methodName, Class<T> classType, Class<?>[] paramTypes, Object[] vals) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		T t = (T)classType.newInstance();
		Method method = classType.getDeclaredMethod(methodName, paramTypes);
		return (R)method.invoke(t, vals);
	}
	
}
