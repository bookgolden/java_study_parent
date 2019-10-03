package com.java.cn;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.RepeatedTest;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("测试TestInfoRepetitionInfoTestReport")
public class TestInfoRepetitionInfoTestReport {

    //------------------Repeated--RepetitionInfo----------------------
    @BeforeEach
    void beforEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        // 当前重复次数
        int curNum = repetitionInfo.getCurrentRepetition();
        // 总重复次数
        int totalNum = repetitionInfo.getTotalRepetitions();
        // 测试方法名
        String methodName = testInfo.getTestMethod().get().getName();
        System.out.println(String.format("开始测试 repetition %d of %d for %s",
                curNum, totalNum, methodName));
    }

    // 重复5次测试
    @RepeatedTest(5)
    void repeatTest1(RepetitionInfo repetitionInfo) {
        System.out.println(repetitionInfo.getTotalRepetitions());
    }

    //----------------------TestReport----------------------
    //可以使用TestReporter发布关于当前测试运行的其他数据
    @Test
    void reportSingleValue(TestReporter testReporter) {
        testReporter.publishEntry("a key", "a value");
    }

    @Test
    void reportSeveralValues(TestReporter testReporter) {
        HashMap<String, String> values = new HashMap<>();
        values.put("user name", "dk38");
        values.put("award year", "1974");

        testReporter.publishEntry(values);
    }


    //----------------------TestInfo----------------------

    public TestInfoRepetitionInfoTestReport(TestInfo testInfo) {
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
