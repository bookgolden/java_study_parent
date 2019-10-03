package com.java.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.time.Duration.ofMillis;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class AssumptionsTest {

    @Test
    void test1_fail() {
        Assertions.fail("测试失败了");
    }

    @Test
    void test2_assertEquals() {
        // 对象内容相同
        assertEquals("abc", new String("abc"));
    }

    @Test
    void test2_assertNotEquals() {
        // 对象内容不同
        assertNotEquals(2, 3);
    }

    @Test
    void test3_assertSame() {
        // 同一个对象（引用地址）
        assertSame("abc", "abc");
    }

    @Test
    void test3_asserNottSame() {
        // 不同的对象（引用地址）
        assertNotSame("abc", "abcd");
    }

    @Test
    void test4_assertTrue() {
        // 是否为真
        assertTrue(5 > 1);
    }

    @Test
    void test4_assertFalse() {
        // 是否为假
        assertFalse(-1 > 1);
    }

    @Test
    void test5_assertNull() {
        // 是否为空
        assertNull(null);
    }

    @Test
    void test5_assertNotNull() {
        // 是否不为空
        assertNotNull(new Object());
    }

    @Test
    void test6_assertThrows() {
        // 是否抛出指定异常
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
        assertEquals("a message", exception.getMessage());
    }

    @Test
    void test6_assertDoesNotThrow() {
        // 是否没抛出异常
        assertDoesNotThrow(() -> {
            int a = 2;
        });
    }

    @Test
    void test7_assertAll() {
        // 是否全相等，参数：[标题,多个对比对]
        assertAll("names", () -> assertEquals("John", "John"), () -> assertEquals("Doe", "Doe"));
    }

    @Test
    void test8_assertArrayEquals() {
        // 数组是否相等
        int[] arr1 = { 1, 2, 3 };
        int[] arr2 = { 1, 2, 3 };
        assertArrayEquals(arr1, arr2);
    }

    @Test
    void test9_assertIterableEquals() {
        // 迭代是否相等
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        assertIterableEquals(list1, list2);
    }

    @Test
    void test10_assertTimeout() {
        // 是否超时（会显示超出时间）
        assertTimeout(ofMillis(10), () -> {
            Thread.sleep(100);
        });
    }

    @Test
    void test11_assertTimeout() {
        // 是否超时（不显示超出时间）
        assertTimeoutPreemptively(ofMillis(10), () -> {
            Thread.sleep(100);
        });
    }

    @Test
    void test12_assertThat() {
        // 匹配（不像junit4自带的assertThat有匹配API，junit5需要第三方库，这里用了Hamcrest）
        // 模式类似于正则表达式
        assertThat((5 - 2), is(equalTo(3)));
//        assertThat("DeveloperWorks", endsWith("Works"));
    }

    @Test
    void test13_assertLinesMatch() {
        // 是否字符串列表全部相等
        List<String> list1 = Arrays.asList("a", "b", "c");
        List<String> list2 = Arrays.asList("a", "b", "c");
        assertLinesMatch(list1, list2);
    }

}
