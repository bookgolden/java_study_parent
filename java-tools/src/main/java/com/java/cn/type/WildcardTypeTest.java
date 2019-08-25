package com.java.cn.type;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

public class WildcardTypeTest {

	public static void main(String[] args) {
//		WildcardType();
		parseClass(PointWildcardImpl.class);
	}

	public static void WildcardType() {
		Class<?> clazz = PointWildcardImpl.class;
		// 此时的type对应PointSingleInterface<Comparable<? extends Number>>
		Type[] types = clazz.getGenericInterfaces();
		for (Type type : types) {
			if (type instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				// 得到填充PointSingleInterface的具体参数，即：Comparable<? extends
				// Number>，仍然是一个ParameterizedType
				Type[] actualTypes = parameterizedType.getActualTypeArguments();
				for (Type actualType : actualTypes) {
					if (actualType instanceof ParameterizedType) {
						ParameterizedType ComparableType = (ParameterizedType) actualType;
						// 对Comparable<? extends Number>再取填充参数，得到的type对应<? extends
						// Number>，这个就是WildcardType了
						Type[] compareArgs = ComparableType.getActualTypeArguments();
						for (Type Arg : compareArgs) {
							if (Arg instanceof WildcardType) {
								// 将得到的对应WildcardType的type强转为WildcardType的变量
								WildcardType wt = (WildcardType) Arg;

								// 利用getLowerBounds得到下界，即派生自Super的限定，如果没有派生自super则为null
								Type[] lowerBounds = wt.getLowerBounds();
								for (Type bound : lowerBounds) {
									Class<?> boundClass = (Class) bound;
									System.out.println("lowerBound为：" + boundClass.getName());
								}

								// 通过getUpperBounds得到上界，即派生自extends的限定，如果没有，默认是Object
								Type[] upperBounds = wt.getUpperBounds();
								for (Type bound : upperBounds) {
									Class<?> boundClass = (Class) bound;
									// 如果不写，则默认输出Object，如果写了，则输出对应的
									System.out.println("upperBound为：" + boundClass.getName());
								}
							}
						}
					}
				}
			}
		}
	}

	private static void parseClass(Class<?> c) {
		parseTypeParameters(c.getGenericInterfaces());
	}

	private static void parseTypeParameters(Type[] types) {
		for (Type type : types) {
			parseTypeParameter(type);
		}
	}

	private static void parseTypeParameter(Type type) {
		if (type instanceof Class) {
			Class<?> c = (Class<?>) type;
			System.out.println(c.getSimpleName());
		} else if (type instanceof TypeVariable) {
			TypeVariable<?> tv = (TypeVariable<?>) type;
			System.out.println(tv.getName());
			parseTypeParameters(tv.getBounds());
		} else if (type instanceof WildcardType) {
			WildcardType wt = (WildcardType) type;
			System.out.println("?");
			parseTypeParameters(wt.getUpperBounds());
			parseTypeParameters(wt.getLowerBounds());
		} else if (type instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) type;
			Type t = pt.getOwnerType();
			if (t != null) {
				parseTypeParameter(t);
			}
			parseTypeParameter(pt.getRawType());
			parseTypeParameters(pt.getActualTypeArguments());
		} else if (type instanceof GenericArrayType) {
			GenericArrayType arrayType = (GenericArrayType) type;
			Type t = arrayType.getGenericComponentType();
			parseTypeParameter(t);
		}
	}
}
