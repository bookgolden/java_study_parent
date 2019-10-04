package com.java.cn.parameter;

import java.util.stream.Stream;

public class StringsProviders {
    static Stream<String> tinyStrings() {
        return Stream.of(".", "oo", "OOO");
    }
}
