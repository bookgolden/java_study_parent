package com.java.cn.bigdecimal;

public class Tests {

    public static <T extends Comparable> T min(T... a) {
        T smallest = a[0];
        for (T item : a) {
            if (smallest.compareTo(item)) {
                smallest = item;
            }
        }
        return smallest;
    }


    public static void main(String[] args) {
        StringCompare result = min(new StringCompare("123"), new StringCompare("234"), new StringCompare("59897"));
        System.out.println(result);
    }
}
