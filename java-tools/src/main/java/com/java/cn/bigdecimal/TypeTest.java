package com.java.cn.bigdecimal;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class TypeTest {
	public static void main(String[] args) {
//		TypeVariable();
		GenericArrayType();
	}

	public static void TypeVariable() {
		Class<?> clazz = PointGenericityImpl.class;
		Type[] types = clazz.getGenericInterfaces();
		for (Type type : types) {
			if (type instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				// 返回表示此类型实际类型参数的 Type 对象的数组
				Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
				for (Type parameterArgType : actualTypeArguments) {

					if (parameterArgType instanceof TypeVariable) {
						TypeVariable typeVariable = (TypeVariable) parameterArgType;
						System.out.println("此接口的填充类型为：" + typeVariable.getName());

						// 返回表示此类型变量上边界的 Type 对象的数组。
						Type[] typebounds = typeVariable.getBounds();
						for (Type bound : typebounds) {
							Class<?> boundClass = (Class) bound;
							// 如果不写，则默认输出Object，如果写了，则输出对应的
							System.out.println("bound为：" + boundClass.getName());
						}
					}
					if (parameterArgType instanceof Class) {
						Class parameterArgClass = (Class) parameterArgType;
						System.out.println("此接口的填充类型为：" + parameterArgClass.getName());
					}
				}
			}
		}
	}

	public static void GenericArrayType() {
		Class<?> clazz = PointArrayImpl.class;
		Type[] interfaces = clazz.getGenericInterfaces();
		for (Type type : interfaces) {
			if (type instanceof ParameterizedType) {
				ParameterizedType pt = (ParameterizedType) type;
				Type[] actualArgs = pt.getActualTypeArguments();
				for (Type arg : actualArgs) {
					if (arg instanceof GenericArrayType) {
						GenericArrayType arrayType = (GenericArrayType) arg;
						Type comType = arrayType.getGenericComponentType();
						Class<?> typeClass = (Class) comType;
						System.out.println("数组类型为：" + typeClass.getName());
					}
				}
			}
		}
	}
}
