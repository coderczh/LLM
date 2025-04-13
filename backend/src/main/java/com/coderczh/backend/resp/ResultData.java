package com.coderczh.backend.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 返回数据封装类
 *
 * @param <T> 数据类型
 * @author 天天向上
 */
@Data
@Accessors(chain = true)
public class ResultData<T> {

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 返回时间戳
     */
    private Long timestamp;

    /**
     * 无参构造器
     */
    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 成功
     *
     * @param data 返回数据
     * @param <T>  数据类型
     * @return 成功的返回报文
     */
    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<>();
        return resultData.setCode(ReturnCodeEnum.RC200.getCode())
                .setMessage(ReturnCodeEnum.RC200.getMessage())
                .setData(data);
    }

    /**
     * 失败
     *
     * @param code    返回代码
     * @param message 返回信息
     * @param <T>     数据类型
     * @return 失败的返回报文
     */
    public static <T> ResultData<T> fail(String code, String message) {
        ResultData<T> resultData = new ResultData<>();
        return resultData.setCode(code)
                .setMessage(message);
    }
}
