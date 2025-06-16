package com.coderczh.backend.service.strategy.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderczh.backend.dao.UserInfoDao;
import com.coderczh.backend.dto.LoginInputDTO;
import com.coderczh.backend.dto.LoginOutputDTO;
import com.coderczh.backend.entity.UserInfo;
import com.coderczh.backend.resp.ResultData;
import com.coderczh.backend.resp.ReturnCodeEnum;
import com.coderczh.backend.service.strategy.LoginStrategy;
import jakarta.annotation.Resource;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Component;

@Component("ACCOUNT")
public class PhoneLogin implements LoginStrategy {

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private StringEncryptor stringEncryptor;

    @Override
    public ResultData<LoginOutputDTO> login(String register, LoginInputDTO loginInputDTO) {
        String notRegister = "1";
        // 登录
        if (notRegister.equals(register)) {
            QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
            wrapper.eq("account_no", loginInputDTO.getAccountNo());
            UserInfo userInfo = userInfoDao.selectOne(wrapper);
            if (userInfo == null) {
                return ResultData.fail(ReturnCodeEnum.USER_INFO_ERR.getCode(), ReturnCodeEnum.USER_INFO_ERR.getMessage());
            } else {
                String password = userInfo.getPassword();
                String decrypt = stringEncryptor.decrypt(password);
                return StrUtil.equals(decrypt, loginInputDTO.getPassword()) ?
                        ResultData.success(Convert.convert(LoginOutputDTO.class, userInfo))
                        : ResultData.fail(ReturnCodeEnum.USER_INFO_ERR.getCode(), ReturnCodeEnum.USER_INFO_ERR.getMessage());
            }
        } else {
            // 注册
            int registerCount = 1;
            String password = stringEncryptor.encrypt(loginInputDTO.getPassword());
            loginInputDTO.setPassword(password);
            UserInfo userInfo = Convert.convert(UserInfo.class, loginInputDTO);
            if (userInfoDao.insert(userInfo) == registerCount) {
                LoginOutputDTO loginOutputDTO = new LoginOutputDTO();
                loginOutputDTO.setAccountNo(loginInputDTO.getAccountNo());
                return ResultData.success(loginOutputDTO);
            } else {
                return ResultData.fail(ReturnCodeEnum.USER_REGISTER_ERR.getCode(),
                        ReturnCodeEnum.USER_REGISTER_ERR.getMessage());
            }
        }
    }
}
