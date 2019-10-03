package com.java.cn.parameter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Test_NameDefine {

    // 自定义名称显示
    @DisplayName("Display name of container")
    @ParameterizedTest(name = "{index} ==> [{arguments}]  first=''{0}'', second={1}")
    @CsvSource({"foo, 1", "bar, 2", "'baz, qux', 3"})
    void test1(String first, int second) {
    }
}
