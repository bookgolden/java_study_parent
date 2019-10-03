package com.java.cn.parameter;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Test_ArgumentsSource {

    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    void test1(String argument) {
        assertNotNull(argument);
    }
}

// 自定义类
class MyArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of("aaa", "bbb").map(Arguments::of);
    }
}


