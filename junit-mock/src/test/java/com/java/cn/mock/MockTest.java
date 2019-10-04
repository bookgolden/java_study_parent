package com.java.cn.mock;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MockTest {

    // 配置mock对象
    @Test
    void test1_thenReturn() {
        List mock = mock(List.class);
        when(mock.get(0)).thenReturn(1);
        assertEquals("预期返回1", 1, mock.get(0));
    }

    @Test
    public void verify_behaviour() {
        //模拟创建一个List对象
        List mock = mock(List.class);
        //使用mock的对象
        mock.add(1);
        mock.clear();
        //验证add(1)和clear()行为是否发生
        verify(mock).add(1);
        verify(mock).clear();
    }

}
