package com.java.cn.parameter.convert;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// 显示转换，
public class Test_ViewConvert {

    //官方例子：数据源是枚举TimeUnit，它转化时采用String.valueOf(source)进行
    @ParameterizedTest
    @EnumSource(TimeUnit.class)
    void test1(
            @ConvertWith(ToStringArgumentConverter.class) String argument) {
        // 转化好后，再反转回来。。。肯定不为null的，所以测试通过
        assertNotNull(TimeUnit.valueOf(argument));
    }
}

class ToStringArgumentConverter extends SimpleArgumentConverter {
    // 采用String.valueOf(source)进行转化
    @Override
    protected Object convert(Object source, Class<?> targetType) {
        assertEquals(String.class, targetType, "Can only convert to String");
        return String.valueOf(source);
    }
}



