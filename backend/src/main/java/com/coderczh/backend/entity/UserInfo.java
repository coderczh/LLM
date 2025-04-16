package com.coderczh.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author 天天向上
 * @since 2025-04-16 14:49:06
 */
@Data
@TableName("tbl_user_info")
public class UserInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 3152862247589524593L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 性别
     */
    @TableField("gender")
    private String gender;

    /**
     * 密码
     */
    @TableField("password")
    private String password;
}
