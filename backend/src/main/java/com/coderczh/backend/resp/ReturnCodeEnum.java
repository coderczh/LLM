package com.coderczh.backend.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回码枚举
 *
 * @author 天天向上
 */
@Getter
@AllArgsConstructor
public enum ReturnCodeEnum {

    /**
     * 系统级返回码
     */
    RC200("200", "操作成功"),
    RC201("201", "服务开启限流熔断保护，请稍后再试"),
    RC202("202", "热点参数限流，请稍后再试"),
    RC203("203", "系统规则不满足要求，请稍后再试"),
    RC204("204", "授权规则不通过，请稍后再试"),
    RC403("403", "无权限访问"),
    RC401("404", "页面找不到"),
    RC500("500", "系统异常，请稍后再试"),

    /**
     * 全局性错误
     */
    PARAM_CHECK_ERROR("9999", "参数校验错误"),

    /**
     * 业务级返回码
     */
    REQUEST_FREQUENT_ERR("1000", "请求频繁，请稍后再试"),
    USER_INFO_EMPTY("1001", "用户信息不存在"),
    PASSWORD_ERR("1002", "密码错误"),
    VERIFY_CODE_ERR("1003", "验证码错误"),
    USER_REGISTER_ERR("1004", "注册失败，请重试"),
    USER_INFO_EXIST_ERR("1010", "用户已存在"),
    ACCOUNT_NO_EMPTY("1005", "帐户号不能为空"),
    PASSWORD_EMPTY("1006", "密码不能为空"),
    PHONE_NO_EMPTY("1007", "手机号码不能为空"),
    VERIFY_CODE_EMPTY("1008", "验证码不能为空"),
    LOGIN_TYPE_ERR("1009", "登录类型错误");

    /**
     * 返回码
     */
    private final String code;

    /**
     * 返回信息
     */
    private final String message;
}
