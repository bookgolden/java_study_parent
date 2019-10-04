package com.java.cn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("这是一个计算测试")
class DisplayNameTest {

    @Test
    @DisplayName("测试1")
    void test1() {
    }

    @DisplayName("测试2")
    @Test
    void test2() {
    }
}