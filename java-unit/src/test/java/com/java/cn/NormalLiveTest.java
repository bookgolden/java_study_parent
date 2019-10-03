package com.java.cn;

import org.junit.jupiter.api.*;

public class NormalLiveTest {

    // BeforeAll必须是静态方法
    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @Test
    void test1() {
        System.out.println("@Test1");
    }

	@Test
	void test2() {
		System.out.println("@Test2");
	}

    @AfterEach
    void afterEach() {
        System.out.println("@AfterEach");
    }

    // AfterAll必须是静态方法
    @AfterAll
    static void afterAll() {
        System.out.println("@AfterAll");
    }

}
