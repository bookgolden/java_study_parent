package com.java.cn.dao;

import com.java.cn.bean.Player;

/**
 * 玩家的数据库操作
 */
public interface PlayerDAO {
    /**
     * 查找玩家
     */
    public Player fetch(int id);

    /**
     * 更新玩家
     */
    public void update(Player player);
}
