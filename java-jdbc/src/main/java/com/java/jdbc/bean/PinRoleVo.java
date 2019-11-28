package com.java.jdbc.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class PinRoleVo implements Serializable {
	
    /* 自增ID */
    private Long id;

    /* 用户pin */
    private String pin;

    /* 角色ID */
    private Long roleId;

    /* 扩展字段 */
    private String ext;

    /* 创建时间 */
    private Date createTime;

    /* 更新时间 */
    private Date updateTime;

    /* 用户姓名 */
    private String userName;

}