package com.java.cn.mock;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

/**
 * 28
 */
public class ThenAnswerTest {

    // thenReturn 是返回结果是我们写死的。如果要让被测试的方法不写死，那么我们用thenAnswer
    @Test
    void test1_thenAnswer() {
        List list = mock(List.class);
        Answer answer = new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                // 获取参数
                Object[] args = invocation.getArguments();
                // 获取方法
                Method method = invocation.getMethod();
                // 获取mock对象类名：List$MockitoMock$1159026000
                String mockClsName = invocation.getMock().getClass().getSimpleName().substring(0, 4);

                return mockClsName + "." + method.getName() + "() 传入参数 " + Arrays.toString(args);
            }

        };

        when(list.get(anyInt())).thenAnswer(answer);
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));

    }
}