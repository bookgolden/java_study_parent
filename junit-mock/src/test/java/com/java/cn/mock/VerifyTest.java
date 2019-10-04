package com.java.cn.mock;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * 29 Mock的验证操作
 */
public class VerifyTest {

    // 常规验证
    @Test
    void test1_verify_normal() {
        List mockList = mock(List.class);
        mockList.add("one");
        mockList.add("two");
        verify(mockList).add("one"); // 如果times不传入，则默认是1
    }

    // 验证参数传入
    @Test
    void test2_verify_param() {
        Map mockMap = mock(Map.class);
        when(mockMap.get("city")).thenReturn("杭州");
        mockMap.get("city");

        // 关注是否有参数传入，注意这个次数只有1次
        verify(mockMap).get(eq("city"));
    }

    // 验证调用的次数
    @Test
    void test3_verify_times() {
        Map mockMap = mock(Map.class);
        when(mockMap.get("city")).thenReturn("杭州");
        mockMap.get("city");
        mockMap.get("city");
        mockMap.get("city");
        // 关注调用次数
        verify(mockMap, times(3)).get(eq("city"));
    }

}
