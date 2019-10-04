package com.java.cn.service;

import com.java.cn.bean.User;

public interface UserService {
    /**
     * 创建新用戶
     */
    void createNewUser(User user) throws Exception;
}
