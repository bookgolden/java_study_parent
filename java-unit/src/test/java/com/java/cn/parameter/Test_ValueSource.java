package com.java.cn.parameter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Test_ValueSource {

    @ParameterizedTest
    @ValueSource(strings = {"aaa", "bbb", "ccc"})
    void test1(String str) {
        assertFalse(str.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2,})
    void test2(int value) {
        assertTrue(value < 5);
    }
}