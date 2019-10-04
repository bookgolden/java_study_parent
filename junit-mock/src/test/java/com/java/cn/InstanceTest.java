package com.java.cn;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

// 配置文件也加了 junit-platform.properties
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InstanceTest {

    public InstanceTest() {
        System.out.println("构造一个MyTest");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("beforeEach");
    }

    @Test
    void test1() {
        System.out.println("test1");
    }

    @Test
    void test2() {
        System.out.println("test2");
    }

    @AfterEach
    void afterEach() {
        System.out.println("afterEach");
        System.out.println("-------------------");
    }

}
