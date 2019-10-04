package com.java.cn.example.one;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.java.cn.bean.User;
import com.java.cn.dao.UserDao;
import com.java.cn.service.UserService;
import com.java.cn.service.impl.UserServiceImpl;

/**
 * https://blog.csdn.net/kimylrong/article/details/51026443
 *
 * 前三个测试案例，测试的是服务对非法输入参数的处理，用不到UserDao，无需mock。
 * 第4个测试案例测试的是服务的处理逻辑，UserService如何处理新增已经存在用户的问题，我们期望报错。判断用户是否存在，需要用到UserDao.queryUser接口，所以我们mock queryUser方法。当参数是1L时，返回一个User对象。
 * 第5个测试案例测试Dao抛异常的情况.
 * 第6个测试案例测试正常处理的情形，doAnswer只是展示无返回值Method的用法，可以不写。
 */
public class UserServiceTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNullUser() throws Exception {
        UserService userService = new UserServiceImpl();

        // 创建mock
        UserDao userDao = mock(UserDao.class);
        ((UserServiceImpl) userService).setUserDao(userDao);

        userService.createNewUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullUserId() throws Exception {
        UserService userService = new UserServiceImpl();

        // 创建mock
        UserDao userDao = mock(UserDao.class);
        ((UserServiceImpl) userService).setUserDao(userDao);

        User user = new User();
        user.setId(null);
        userService.createNewUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullUserName() throws Exception {
        UserService userService = new UserServiceImpl();

        // 创建mock
        UserDao userDao = mock(UserDao.class);
        ((UserServiceImpl) userService).setUserDao(userDao);

        User user = new User(1L, "");
        userService.createNewUser(user);
    }

    @Test(expected = Exception.class)
    public void testCreateExistUser() throws Exception {
        UserServiceImpl userService = new UserServiceImpl();

        // 创建mock
        UserDao userDao = mock(UserDao.class);
        User returnUser = new User(1L, "Vikey");
        when(userDao.queryUser(1L)).thenReturn(returnUser);
        ((UserServiceImpl) userService).setUserDao(userDao);

        User user = new User(1L, "Vikey");
        userService.createNewUser(user);
    }

    @Test(expected = Exception.class)
    public void testCreateUserOnDatabaseException() throws Exception {
        UserService userService = new UserServiceImpl();

        // 创建mock
        UserDao userDao = mock(UserDao.class);
        doThrow(new SQLException("SQL is not valid")).when(userDao).insertUser(any(User.class));
        ((UserServiceImpl) userService).setUserDao(userDao);

        User user = new User(1L, "Vikey");
        userService.createNewUser(user);
    }

    @Test
    public void testCreateUser() throws Exception {
        UserService userService = new UserServiceImpl();

        // 创建mock
        UserDao userDao = mock(UserDao.class);
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) throws Throwable {
                System.out.println("Insert data into user table");
                return null;
            }
        }).when(userDao).insertUser(any(User.class));
        ((UserServiceImpl) userService).setUserDao(userDao);

        User user = new User(1L, "Vikey");
        userService.createNewUser(user);
    }
}
