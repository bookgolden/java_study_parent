package com.java.cn.parameter.convert;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

// JUnit Jupiter 还提供了一种回调机制，自动转换从一个字符串转化为相应对象
public class Test_Convert_StringToObject {

    @ParameterizedTest
    @ValueSource(strings = "从你的全世界路过")
    void testWithImplicitFallbackArgumentConversion(Book book) {
        assertEquals("从你的全世界路过", book.getTitle());
    }
}

class Book {

    private final String title;

    private Book(String title) {
        this.title = title;
    }

    public static Book fromTitle(String title) {
        System.out.println("工厂方法构建Book");
        return new Book(title);
    }

    public String getTitle() {
        return this.title;
    }
}
