package com.coderczh.backend.service.strategy.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderczh.backend.common.Constant;
import com.coderczh.backend.dao.UserInfoDao;
import com.coderczh.backend.dto.LoginInputDTO;
import com.coderczh.backend.dto.LoginOutputDTO;
import com.coderczh.backend.entity.UserInfo;
import com.coderczh.backend.resp.ResultData;
import com.coderczh.backend.resp.ReturnCodeEnum;
import com.coderczh.backend.service.strategy.LoginStrategy;
import io.github.yindz.random.RandomSource;
import io.github.yindz.random.source.PersonInfoSource;
import jakarta.annotation.Resource;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component(Constant.LOGIN_TYPE_PHONE)
public class PhoneLogin implements LoginStrategy {

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private StringEncryptor stringEncryptor;

    @Override
    public ResultData<LoginOutputDTO> login(String register, LoginInputDTO loginInputDTO) {
        // 注册
        if (Constant.REGISTER_FLAG_TRUE.equals(register)) {
            UserInfo userInfo = getUserInfo(loginInputDTO);
            if (userInfoDao.insert(userInfo) == 1) {
                LoginOutputDTO loginOutputDTO = new LoginOutputDTO();
                loginOutputDTO.setNickName(userInfo.getNickName());
                return ResultData.success(loginOutputDTO);
            } else {
                return ResultData.fail(ReturnCodeEnum.USER_REGISTER_ERR.getCode(),
                        ReturnCodeEnum.USER_REGISTER_ERR.getMessage());
            }
        } else {
            QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
            wrapper.eq("phone_no", loginInputDTO.getPhoneNo());
            UserInfo userInfo = userInfoDao.selectOne(wrapper);
            if (userInfo == null) {
                return ResultData.fail(ReturnCodeEnum.PHONE_INFO_ERR.getCode(), ReturnCodeEnum.PHONE_INFO_ERR.getMessage());
            } else {
                String password = userInfo.getPassword();
                String decrypt = stringEncryptor.decrypt(password);
                return StrUtil.equals(decrypt, loginInputDTO.getPassword()) ?
                        ResultData.success(Convert.convert(LoginOutputDTO.class, userInfo))
                        : ResultData.fail(ReturnCodeEnum.PHONE_INFO_ERR.getCode(), ReturnCodeEnum.PHONE_INFO_ERR.getMessage());
            }
        }
    }

    private UserInfo getUserInfo(LoginInputDTO loginInputDTO) {
        UserInfo userInfo = Convert.convert(UserInfo.class, loginInputDTO);
        PersonInfoSource personInfoSource = RandomSource.personInfoSource();
        Date date = new Date();
        return userInfo.setNickName(personInfoSource.randomQQNickName())
                .setCreateDate(date)
                .setCreateTime(date);
    }
}
