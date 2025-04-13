package com.coderczh.backend.common;

import com.alibaba.fastjson2.JSONObject;
import com.coderczh.backend.resp.ResultData;
import com.coderczh.backend.resp.ReturnCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultData<JSONObject> systemExceptionHandler(Exception exception) {
        log.error("交易处理异常", exception);
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), ReturnCodeEnum.RC500.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultData<JSONObject> validExceptionHandler(MethodArgumentNotValidException exception) {
        ResultData<JSONObject> resultData = new ResultData<>();
        try {
            resultData = ResultData.fail(ReturnCodeEnum.PARAM_CHECK_ERROR.getCode(),
                    ReturnCodeEnum.PARAM_CHECK_ERROR.getMessage());
            FieldError fieldError = exception.getBindingResult().getFieldError();
            log.info(fieldError.getDefaultMessage());
        } catch (Exception e) {
            systemExceptionHandler(e);
        }
        return resultData;
    }
}
