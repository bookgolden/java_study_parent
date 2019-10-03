package com.java.cn.parameter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class Test_CsvSource {

    // 测试1
    @ParameterizedTest
    @CsvSource({"aaa, 1", "bbb, 2"})
    void test1(String first, int second) {
        assertNotNull(first);
        assertNotEquals(0, second);
    }

    // 测试2，有逗号时，用单引号包裹参数
    @ParameterizedTest
    @CsvSource({"'baz,qux', 333" })
    void test2(String first, int second) {
        assertNotNull(first);
        assertNotEquals(0, second);
    }

    // 测试3，空字符串用''表示
    @ParameterizedTest
    @CsvSource({"'', 333" })
    void test3(String first, int second) {
        assertNotNull(first);
        assertNotEquals(0, second);
    }

    // 测试4，null用逗号隔开即可
    @ParameterizedTest
    @CsvSource({", 333" })
    void test4(String first, int second) {
        assertNull(first);
        assertNotEquals(0, second);
    }
}
