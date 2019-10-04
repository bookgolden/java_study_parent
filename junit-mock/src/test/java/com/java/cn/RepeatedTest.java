package com.java.cn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepeatedTest {
    @BeforeEach
    void beforEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        // 当前重复次数
        int curNum = repetitionInfo.getCurrentRepetition();
        // 总重复次数
        int totalNum = repetitionInfo.getTotalRepetitions();
        // 测试方法名
        String methodName = testInfo.getTestMethod().get().getName();
        System.out.println(String.format("开始测试 repetition %d of %d for %s", curNum, totalNum, methodName));
    }

    // 重复5次测试
    @org.junit.jupiter.api.RepeatedTest(5)
    void repeatTest1() {
        assertEquals(100, 100);
    }

    @org.junit.jupiter.api.RepeatedTest(value = 1, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("repeatTest2!")
    void repeatTest2(TestInfo testInfo) {
        assertEquals(testInfo.getDisplayName(), "repeatTest2! 1/1");
    }
}
