package com.java.cn;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

// 类上加@Disabled，则所有测试方法都跳过
@Disabled
public class DisabledTest {

    // 此方法不是执行
    @Disabled
    @Test
    void test(){
        assertEquals(2, 3);
    }
}
