package com.coderczh.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author 天天向上
 * @since 2025-04-16 14:49:06
 */
@Data
@Accessors(chain = true)
@TableName("tbl_user_info")
public class UserInfo {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;

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
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 性别
     */
    @TableField("gender")
    private String gender;

    /**
     * 身份证号
     */
    @TableField("id_card")
    private String idCard;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 手机号
     */
    @TableField("phone_no")
    private String phoneNo;

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
     * 创建日期
     */
    @TableField("create_date")
    private Date createDate;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新日期
     */
    @TableField("update_date")
    private Date updateDate;

    /**
     * 更新日期
     */
    @TableField("update_time")
    private Date updateTime;
}
