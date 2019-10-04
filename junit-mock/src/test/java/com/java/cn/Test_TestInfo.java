package com.java.cn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

@DisplayName("测试TestInfo")
class Test_TestInfo {

    public Test_TestInfo(TestInfo testInfo) {
        System.out.println("构造中获取DisplayName: " + testInfo.getDisplayName());
    }

    @BeforeEach
    void init(TestInfo testInfo) {
        System.out.println("BeforeEach中获取DisplayName: " + testInfo.getDisplayName());
    }

    @Test
    @DisplayName("TEST1")
    @Tag("my-tag")
    void test1(TestInfo testInfo) {
        //testInfo获取显示名称
        assertEquals("TEST1", testInfo.getDisplayName());
        //testInfo获取tag信息
        assertTrue(testInfo.getTags().contains("my-tag"));
        //testInfo获取测试方法名
        assertEquals("test1", testInfo.getTestMethod().get().getName());
    }
}
