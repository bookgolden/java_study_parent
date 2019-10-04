package com.java.cn.example.one;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.cn.bean.User;
import com.java.cn.dao.UserDao;
import com.java.cn.service.impl.UserServiceImpl;

/**
 * springboot版本的测试
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
public class UserServiceBootTests {
    @InjectMocks
    private UserServiceImpl userService;
    //创建mock
    @Mock
    private UserDao userDao;

    @BeforeEach
    void beforeEach() {
        // 初始化测试用例类中由Mockito的注解标注的所有模拟对象 【配合@RunWith(SpringRunner.class)使用】
        MockitoAnnotations.initMocks(this);
        // 用模拟对象创建测试类对象
        //UserService userService = new UserServiceImpl();
    }

    @Test
    public void contextLoads() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullUser() throws Exception {
        userService.setUserDao(userDao);
        userService.createNewUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullUserId() throws Exception {
        userService.setUserDao(userDao);
        userService.createNewUser(null);

        User user = new User();
        user.setId(null);
        userService.createNewUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullUserName() throws Exception {
        userService.setUserDao(userDao);
        userService.createNewUser(null);

        User user = new User(1L, "");
        userService.createNewUser(user);
    }

    @Test(expected = Exception.class)
    public void testCreateExistUser() throws Exception {
        User returnUser = new User(1L, "Vikey");
        when(userDao.queryUser(1L)).thenReturn(returnUser);
        userService.setUserDao(userDao);

        User user = new User(1L, "Vikey");
        userService.createNewUser(user);
    }

    @Test(expected = Exception.class)
    public void testCreateUserOnDatabaseException() throws Exception {
        doThrow(new SQLException("SQL is not valid")).when(userDao).insertUser(any(User.class));
        userService.setUserDao(userDao);

        User user = new User(1L, "Vikey");
        userService.createNewUser(user);
    }

    @Test
    public void testCreateUser() throws Exception {
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) throws Throwable {
                System.out.println("Insert data into user table");
                return null;
            }
        }).when(userDao).insertUser(any(User.class));
        userService.setUserDao(userDao);

        User user = new User(1L, "Vikey");
        userService.createNewUser(user);
    }

}
