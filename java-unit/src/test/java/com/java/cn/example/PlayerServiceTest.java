package com.java.cn.example;

import com.java.cn.bean.Player;
import com.java.cn.dao.PlayerDAO;
import com.java.cn.service.PlayerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * 31
 */
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlayerServiceTest {
    @Mock
    private PlayerDAO playerDAO; // 模拟对象

    private PlayerService playerService; // 被测试的类

//    @BeforeAll
//    void beforeAll(){
//        // 初始化测试用例类中由Mockito的注解标注的所有模拟对象
//        MockitoAnnotations.initMocks(this);
//        // 用模拟对象创建测试类对象
//        playerService = new PlayerService(playerDAO);
//    }

    @BeforeEach
    void beforeEach(){
        // 初始化测试用例类中由Mockito的注解标注的所有模拟对象
        MockitoAnnotations.initMocks(this);
        // 用模拟对象创建测试类对象
        playerService = new PlayerService(playerDAO);
    }

    // 测试：更新玩家名字，张三 -> 李四
    @Test
    public void shouldUpdatePlayerName() {
        Player player = new Player(1, "张三");
        // 打桩：设置模拟对象的返回预期值
        when(playerDAO.fetch(1)).thenReturn(player);
        // 执行测试
        boolean updated = playerService.update(1, "李四");
        // 验证更新是否成功(返回值有问题时，测试会失败)
        assertTrue(updated);
        // 验证模拟对象的fetch()是否被调用了，而且传入了1
        verify(playerDAO).fetch(1);

        // 得到一个参数抓取器
        ArgumentCaptor<Player> playerCaptor = ArgumentCaptor.forClass(Player.class);
        // 验证模拟对象的update()是否被调用一次，并用抓取器抓取调用时的参数
        verify(playerDAO).update(playerCaptor.capture());
        // 获取抓取到的参数值
        Player updatePlayer = playerCaptor.getValue();
        //  验证这个参数值
        assertEquals("李四", updatePlayer.getName());

        // 检查模拟对象上是否还有未验证的测试
        verifyNoMoreInteractions(playerDAO);
    }

    // 测试：如果玩家不存在，就不用update玩家了
    @Test
    void shouldNotUpdateIfPlayerNotFound(){
        // 打桩：设置模拟对象的返回预期值
        when(playerDAO.fetch(1)).thenReturn(null);
        // 执行测试
        boolean updated = playerService.update(1, "李四");
        // 验证更新是否失败
        assertFalse(updated);
        // 验证playerDAO中的fetch方法是否被调用了1次
        verify(playerDAO).fetch(1);
        // 验证模拟对象是否没有发生任何交互
        verifyZeroInteractions(playerDAO);
    }

    // 原始测试：不用mock
    @Test
    public void testUpdate(){
        int playerId = 1;
        String playerName = "飞利浦";

        PlayerService service = new PlayerService(new PlayerDAO() {
            @Override
            public Player fetch(int id) {
                System.out.println("fetch. Not supported yet.");
                return null;
            }

            @Override
            public void update(Player player) {
                System.out.println("update. Not supported yet.");
            }
        });
        boolean expResult = false;
        boolean result = service.update(playerId, playerName);
        assertEquals(expResult, result);
    }
}
