package com.coderczh.backend.common;

import com.alibaba.fastjson2.JSONObject;
import com.coderczh.backend.resp.ResultData;
import com.coderczh.backend.resp.ReturnCodeEnum;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author 天天向上
 */
@Slf4j
@RestControllerAdvice
@SuppressWarnings(value = "all")
public class GlobalException {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultData<JSONObject> systemExceptionHandler(Exception exception) {
        log.error("交易处理异常", exception);
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), ReturnCodeEnum.RC500.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultData<JSONObject> validExceptionHandler(ConstraintViolationException exception) {
        ResultData<JSONObject> resultData = new ResultData<>();
        resultData = ResultData.fail(ReturnCodeEnum.PARAM_CHECK_ERROR.getCode(),
                ReturnCodeEnum.PARAM_CHECK_ERROR.getMessage());
        log.info("必填参数不能为空，{}", exception);
        return resultData;
    }
}
