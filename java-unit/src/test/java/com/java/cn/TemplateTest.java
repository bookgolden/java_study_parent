package com.java.cn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemplateTest {
    final List<String> fruits = Arrays.asList("apple", "banana", "lemon");

    /** 该测试模板里，会检测apple和banana */
    @DisplayName("水果测试")
    @TestTemplate
    @ExtendWith(MyTestTemplate.class)
    void test(String fruit) {
        assertTrue(fruits.contains(fruit));
    }
}

/**
 * 官方例子：自定义模板
 * 该模板校验字符串，只有匹配字符串 apple和banana的才可以通过测试
 */
class MyTestTemplate implements TestTemplateInvocationContextProvider {
    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        return true;
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        // 检测针对apple和banana的参数
        return Stream.of(invocationContext("apple"), invocationContext("banana"));
    }

    private TestTemplateInvocationContext invocationContext(String parameter) {
        return new TestTemplateInvocationContext() {
            /** 返回显示的名字 */
            @Override
            public String getDisplayName(int invocationIndex) {
                return parameter;
            }

            /** 扩展 */
            @Override
            public List<Extension> getAdditionalExtensions() {
                return Collections.singletonList(new ParameterResolver() {
                    /** 支持的参数必须是字符串类型 */
                    @Override
                    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
                        return parameterContext.getParameter().getType().equals(String.class);
                    }

                    //处理参数，这里没处理，直接返回参数
                    @Override
                    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
                        return parameter;
                    }
                });
            }
        };
    }
}