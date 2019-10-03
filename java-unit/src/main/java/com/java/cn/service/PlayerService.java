package com.java.cn.service;

import com.java.cn.bean.Player;
import com.java.cn.dao.PlayerDAO;

/**
 * 玩家服务
 */
public class PlayerService {
    private final PlayerDAO playerDAO;

    public PlayerService(PlayerDAO dao) {
        this.playerDAO = dao;
    }

    /**
     * 更新玩家名字
     */
    public boolean update(int playerId, String playerName) {
        Player player = this.playerDAO.fetch(playerId);
        if (player != null) {
            player.setName(playerName);
            playerDAO.update(player);
            // 发现bug，此处没写返回值，所以导致测试失败
            return true;
        }
        return false;
    }
}
