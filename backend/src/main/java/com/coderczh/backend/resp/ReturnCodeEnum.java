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
    PARAM_CHECK_ERROR("9000", "参数校验错误");

    /**
     * 返回码
     */
    private final String code;

    /**
     * 返回信息
     */
    private final String message;
}
