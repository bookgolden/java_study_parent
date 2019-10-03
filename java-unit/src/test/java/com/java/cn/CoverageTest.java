package com.java.cn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoverageTest {

    // 语句覆盖测试
    // 只测试了加法
    //test1
    @Test
    void testAdd() {
        Calculator cal = new Calculator();
        int sum = cal.add(1, 1);
        assertEquals(2, sum);
    }

    //test2
    @ParameterizedTest(name = "[{index}] a={0}, b={1}, result={2}")
    // 语句覆盖率100%
    // @CsvSource({"5, 5, 11"})
    // 判定覆盖率100%
    // @CsvSource({"5, 5, 11", "15, 15, 0"})
    // 条件覆盖率100%
    // @CsvSource({"5, 15, 1", "15, 5, 10"})
    // 路径覆盖率100%
    @CsvSource({"5, 5, 11", "15, 5, 10", "5, 15, 1", "15, 15, 0"})
    void testJudge(int a, int b, int result) {
        Judge judge = new Judge();
        assertEquals(result, judge.judge(a, b));
    }

}
