package com.coderczh.backend.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.coderczh.backend.common.util.RedisUtil;
import com.coderczh.backend.dao.UserInfoDao;
import com.coderczh.backend.dto.LoginInputDTO;
import com.coderczh.backend.dto.LoginOutputDTO;
import com.coderczh.backend.resp.ResultData;
import com.coderczh.backend.resp.ReturnCodeEnum;
import com.coderczh.backend.service.LoginService;
import com.coderczh.backend.service.factory.LoginStrategyFactory;
import com.coderczh.backend.service.strategy.LoginStrategy;
import jakarta.annotation.Resource;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private StringEncryptor stringEncryptor;

    private final LoginStrategyFactory loginStrategyFactory;

    @Autowired
    public LoginServiceImpl(LoginStrategyFactory loginStrategyFactory) {
        this.loginStrategyFactory = loginStrategyFactory;
    }

    private static final String LOGIN_ACCOUNT = "ACCOUNT";
    private static final String LOGIN_PHONE = "PHONE";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<LoginOutputDTO> loginByAccount(String loginType, String register, LoginInputDTO loginInputDTO) {
        LoginStrategy loginStrategy = "0".equals(loginType)
                ? loginStrategyFactory.getLoginStrategy(LOGIN_ACCOUNT)
                : loginStrategyFactory.getLoginStrategy(LOGIN_PHONE);
        return loginStrategy.login(register, loginInputDTO);
    }

    @Override
    public ResultData<String> getVerifyCode(String phoneNo) {
        if (StrUtil.isNotBlank(redisUtil.getStr(phoneNo))) {
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
