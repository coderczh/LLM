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
    private static final long serialVersionUID = -6102437406436586960L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 性别
     */
    @TableField("gender")
    private String gender;

    /**
     * 账号
     */
    @TableField("account_no")
    private String accountNo;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 密码
     */
    @TableField("phone_no")
    private String phoneNo;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;
}
