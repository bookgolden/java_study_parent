package com.java.cn.parameter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class Test_MethodSource {

    // 参数测试1，源为MethodSource
    @ParameterizedTest
    @MethodSource("stringProvider")
    void test1(String str) {
        assertNotNull(str);
    }

    // （必须是静态方法）字符串提供者
    static Stream<String> stringProvider() {
        return Stream.of("aaa", "bbb");
    }


    // 参数测试2，采用流作为方法提供者（DoubleStream、IntStream、LongStream）
    @ParameterizedTest
    @MethodSource("range")
    void test2(int argument) {
        assertNotEquals(9, argument);
    }

    static IntStream range() {
        return IntStream.range(0, 20).skip(10);
    }

    // 参数测试3，测试方法有多个参数时，需要用Arguments.of(Object…​)进行包装
    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void test3(String str, int num, List<String> list) {
        assertEquals(3, str.length());
        assertTrue(num >= 1 && num <= 2);
        assertEquals(2, list.size());
    }

    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                Arguments.of("abc", 1, Arrays.asList("a", "b")),
                Arguments.of("xyz", 2, Arrays.asList("x", "y"))
        );
    }

    // 参数测试4，外部的静态工厂方法，完整包名#方法名
    @ParameterizedTest
    @MethodSource("com.java.cn.parameter.StringsProviders#tinyStrings")
    void test4(String tinyString) {
        assertTrue(tinyString.length() < 5);
    }
}
