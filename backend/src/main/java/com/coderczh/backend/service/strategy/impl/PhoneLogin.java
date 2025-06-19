package com.coderczh.backend.service.strategy.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderczh.backend.common.Constant;
import com.coderczh.backend.common.util.RedisUtil;
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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component(Constant.LOGIN_TYPE_PHONE)
public class PhoneLogin implements LoginStrategy {

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private RedisUtil redisUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<LoginOutputDTO> login(String register, LoginInputDTO loginInputDTO) {
        // 注册
        if (Constant.REGISTER_FLAG_TRUE.equals(register)) {
            UserInfo userInfo = getUserInfo(loginInputDTO);
            if (userInfoDao.insert(userInfo) == 1) {
                LoginOutputDTO loginOutputDTO = Convert.convert(LoginOutputDTO.class, userInfo);
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
                return ResultData.fail(ReturnCodeEnum.USER_INFO_EMPTY.getCode(), ReturnCodeEnum.USER_INFO_EMPTY.getMessage());
            } else {
                String verifyCode = redisUtil.getStr(loginInputDTO.getPhoneNo());
                return StrUtil.equals(verifyCode, loginInputDTO.getVerifyCode()) ?
                        ResultData.success(Convert.convert(LoginOutputDTO.class, userInfo))
                        : ResultData.fail(ReturnCodeEnum.VERIFY_CODE_ERR.getCode(), ReturnCodeEnum.VERIFY_CODE_ERR.getMessage());
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
