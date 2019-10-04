package com.java.cn.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

/**
 * 30 Spy对象监视
 */
public class SpyTest {

    @Test
    void test1_spy() {
        List<String> list = new ArrayList<>();
        List<String> spyList = spy(list);

        // 改写size()方法的返回值，变成固定的100
        when(spyList.size()).thenReturn(100);

        // 当使用spy时，会调用真实的方法
        spyList.add("one");
        spyList.add("two");

        // 我们可以打印看一看
        System.out.println("Spy get(0) = " + spyList.get(0));
        System.out.println("Spy get(1) = " + spyList.get(1));

        // size()被打桩了 100
        System.out.println("Spy.size() = " + spyList.size());

        // 看需要，你可以进行验证
        verify(spyList).add("one");
        verify(spyList).add("two");
    }

}
