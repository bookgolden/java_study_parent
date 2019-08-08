package com.java.T;

public class Generic<T> {

	private T key;

	public Generic(T key) {
		this.key = key;
	}

	public T getKey() {
		return key;
	}

//	Generic<Integer> genericInteger = new Generic<Integer>(123456);
//
//	// 传入的实参类型需与泛型的类型参数类型相同，即为String.
//	Generic<String> genericString = new Generic<String>("key_vlaue");

	public static void showKeyValue(Generic<?> obj) {
		System.out.println("泛型测试 , key value is " + obj.getKey());
	}

	public static void main(String[] args) {
//		Generic generic = new Generic("111111");
//		Generic generic1 = new Generic(4444);
//		Generic generic2 = new Generic(55.55);
//		Generic generic3 = new Generic(false);
//		if (generic3 instanceof Generic) {
//			System.out.println(";generic3;");
//		}
		
		Generic<Integer> gInteger = new Generic<Integer>(123);
		Generic<Number> gNumber = new Generic<Number>(456);

		showKeyValue(gInteger);

		// showKeyValue这个方法编译器会为我们报错：Generic<java.lang.Integer> 
		// cannot be applied to Generic<java.lang.Number>
		 showKeyValue(gInteger);
		
	}
}
