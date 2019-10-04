package com.java.cn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

public class Test_RepetitionInfo {
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
}
