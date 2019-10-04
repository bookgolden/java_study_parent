package com.java.cn.mock;

import com.java.cn.Calculator;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;


// Mockito测试
public class Test_Mock {

    // 配置mock对象
    @Test
    void test1_thenReturn() {
        List mock = mock(List.class);
        when(mock.get(0)).thenReturn(1);
        assertEquals("预期返回1", 1, mock.get(0));
    }

    // 迭代风格的返回设定
    @Test
    void test2_iterator() {
        Iterator it = mock(Iterator.class);
        when(it.next()).thenReturn("Hello").thenReturn("World");
        assertEquals("Hello", it.next());
        assertEquals("World", it.next());
    }

    // 无返回值时
    @Test
    void test3_noReturnValue() {
        List list = new LinkedList();
        List spy = spy(list);

        // 当spy调用clear()的时候，不执行任何东西
        doNothing().when(spy).clear();

        spy.add("one");
        // 调用了clear()，不执行任何东西，所以spy里的容量还是1
        spy.clear();

        assertEquals(1, spy.size());
    }

    // 抛出异常
    @Test
    void test4_throwException() {
        Iterator it = mock(Iterator.class);
        // it.next()会抛出异常
        when(it.next()).thenThrow(new RuntimeException("Next Exception"));
        // it.remove()也抛出异常
        doThrow(new RuntimeException("Remove Exception")).when(it).remove();

        // 验证异常it.next()
        Throwable nextException = assertThrows(RuntimeException.class, () -> {
            it.next();
        });
        assertEquals("Next Exception", nextException.getMessage());

        // 验证异常it.remove()
        Throwable removeException = assertThrows(RuntimeException.class, () -> {
            it.remove();
        });
        assertEquals("Remove Exception", removeException.getMessage());
    }

    // 模拟参数传入
    @Test
    void test5_param() {
        List list = mock(List.class);
        when(list.get(1)).thenReturn("aaa");
        assertEquals("aaa", list.get(1));
        assertEquals(null, list.get(999));

        // 可以用anyInt()表示任意整数
        when(list.remove(anyInt())).thenReturn("kkk");
        assertEquals("kkk", list.remove(3));
        assertEquals("kkk", list.remove(66));

        // 可以用any(class)表示任意对象
        when(list.remove(any(Calculator.class))).thenReturn(true);
        assertEquals(true, list.remove(new Calculator()));
    }

}
