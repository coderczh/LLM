package com.coderczh.backend.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.coderczh.backend.common.Constant;
import com.coderczh.backend.common.util.RedisUtil;
import com.coderczh.backend.dto.LoginInputDTO;
import com.coderczh.backend.dto.LoginOutputDTO;
import com.coderczh.backend.resp.ResultData;
import com.coderczh.backend.resp.ReturnCodeEnum;
import com.coderczh.backend.service.LoginService;
import com.coderczh.backend.service.factory.LoginStrategyFactory;
import com.coderczh.backend.service.strategy.LoginStrategy;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private RedisUtil redisUtil;

    private final LoginStrategyFactory loginStrategyFactory;

    @Autowired
    public LoginServiceImpl(LoginStrategyFactory loginStrategyFactory) {
        this.loginStrategyFactory = loginStrategyFactory;
    }

    @Override
    public ResultData<LoginOutputDTO> loginByAccount(String loginType, String register, LoginInputDTO loginInputDTO) {
        LoginStrategy loginStrategy;
        if (Constant.LOGIN_TYPE_ACCOUNT.equals(loginType)) {
            if (StrUtil.isBlank(loginInputDTO.getAccountNo())) {
                return ResultData.fail(ReturnCodeEnum.ACCOUNT_NO_EMPTY.getCode(), ReturnCodeEnum.ACCOUNT_NO_EMPTY.getMessage());
            } else if (StrUtil.isBlank(loginInputDTO.getPassword())) {
                return ResultData.fail(ReturnCodeEnum.PASSWORD_EMPTY.getCode(), ReturnCodeEnum.PASSWORD_EMPTY.getMessage());
            }
            loginStrategy = loginStrategyFactory.getLoginStrategy(Constant.LOGIN_TYPE_ACCOUNT);
        } else if (Constant.LOGIN_TYPE_PHONE.equals(loginType)) {
            if (StrUtil.isBlank(loginInputDTO.getPhoneNo())) {
                return ResultData.fail(ReturnCodeEnum.PHONE_NO_EMPTY.getCode(), ReturnCodeEnum.PHONE_NO_EMPTY.getMessage());
            } else if (StrUtil.isBlank(loginInputDTO.getVerifyCode())) {
                return ResultData.fail(ReturnCodeEnum.VERIFY_CODE_EMPTY.getCode(), ReturnCodeEnum.VERIFY_CODE_EMPTY.getMessage());
            }
            loginStrategy = loginStrategyFactory.getLoginStrategy(Constant.LOGIN_TYPE_PHONE);
        } else {
            return ResultData.fail(ReturnCodeEnum.LOGIN_TYPE_ERR.getCode(), ReturnCodeEnum.LOGIN_TYPE_ERR.getMessage());
        }
        return loginStrategy.login(register, loginInputDTO);
    }

    @Override
    public ResultData<String> getVerifyCode(String phoneNo) {
        if (StrUtil.isNotBlank((String) redisUtil.getStr(phoneNo))) {
            return ResultData.fail(ReturnCodeEnum.REQUEST_FREQUENT_ERR.getCode(),
                    ReturnCodeEnum.REQUEST_FREQUENT_ERR.getMessage());
        } else {
            int verifyCodeLength = 6;
            String verifyCode = RandomUtil.randomNumbers(verifyCodeLength);
            redisUtil.setStr(phoneNo, verifyCode, 60 * 1000);
            return ResultData.success(verifyCode);
        }
    }
}
