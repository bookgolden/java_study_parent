package com.java.cn.bigdecimal;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BgiDecimalTest {
	public static void main(String[] args) throws ClassNotFoundException {

//		BigDecimal bd = new BigDecimal(6982.50000000).setScale(3, RoundingMode.HALF_UP).divide(new BigDecimal(58120.76000000), 4);
//		BigDecimal bd2 = new BigDecimal(10).setScale(3, RoundingMode.HALF_UP).divide(new BigDecimal(2), 2, BigDecimal.ROUND_DOWN);
//		System.out.println(bd);

//		Class<?> clazz = Class.forName(InnerClass.class.getName());
//		int mod = clazz.getModifiers();
//		System.out.println(Modifier.isAbstract(mod));
//		System.out.println(Modifier.isFinal(mod));
//		System.out.println(Modifier.isInterface(mod));
//		System.out.println(Modifier.isPrivate(mod));
//		System.out.println(Modifier.isNative(mod));
//		System.out.println(Modifier.isProtected(mod));
//		System.out.println(Modifier.isPublic(mod));
//		System.out.println(Modifier.isStatic(mod));
//		System.out.println(Modifier.isSynchronized(mod));
//		System.out.println(Modifier.isTransient(mod));
//		System.out.println(Modifier.isVolatile(mod));
//		System.out.println(Modifier.isPublic(mod));
//		System.out.println(Modifier.isStrict(mod));
//		System.out.println(Modifier.toString(mod));

		Class<?> clazz = PointImpl.class;
		Type type = clazz.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			// 返回表示此类型实际类型参数的 Type 对象的数组
			Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
			for (Type parameterArgType : actualTypeArguments) {
				Class parameterArgClass = (Class) parameterArgType;
				System.out.println("填充类型为：" + parameterArgClass.getName());
			}

			// 返回 Type 对象，表示声明此类型的类或接口。
			Type type1 = parameterizedType.getRawType();
			Class class22 = (Class) type1;
			System.out.println("PointImpl的父类类型为：" + class22.getName());

		}
	}

}
