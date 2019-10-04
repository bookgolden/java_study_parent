package com.java.cn;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

public class TagTest {

    @Tag("Some")
    @Test
    void test1() {
    }

    @Tag("Red")
    @Tag("Black")
    @Test
    void test2() {
    }

    @Tags({@Tag("One"), @Tag("Two")})
    @Test
    void test3() {
    }
}
