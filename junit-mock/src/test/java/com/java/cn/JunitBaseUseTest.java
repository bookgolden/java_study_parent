package com.java.cn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 1 JUnitTest_baseUse
 */
class JunitBaseUseTest {

    @Test
    void testAdd() {
        // 期望值
        int expected = 3;
        // 真实值
        int actual = new Calculator().add(1, 2);
        // 断言，判定期望值与真实值是否相等
        assertEquals(expected, actual);

        expected = -1;
        actual = new Calculator().add(1, -2);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1 + 1 = 2")
    void addsTwoNumbers() {
        Calculator calculator = new Calculator();
        assertEquals(2, calculator.add(1, 1), "1 + 1 should equal 2");
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "0,    1,   1",
            "1,    2,   3",
            "49,  51, 100",
            "1,  100, 101"
    })
    void add(int first, int second, int expectedResult) {
        Calculator calculator = new Calculator();
        assertEquals(expectedResult, calculator.add(first, second),
                () -> first + " + " + second + " should equal " + expectedResult);
    }

}