package com.java.cn.parameter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * ValueSource
 * EnumSource
 * MethodSource
 * CsvSource
 * CsvFileSource
 * ArgumentsSource
 */
public class Test_CsvFileSource {
    // 用resources指定文件所在位置，该文件必须在资源路径下
    @ParameterizedTest
    @CsvFileSource(resources = "/test.csv", numLinesToSkip = 1)
    void test1(String first, int second) {
        assertNotNull(first);
        assertNotEquals(0, second);
    }
}
