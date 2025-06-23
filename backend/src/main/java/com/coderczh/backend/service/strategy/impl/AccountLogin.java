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
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component(Constant.LOGIN_TYPE_ACCOUNT)
public class AccountLogin implements LoginStrategy {

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private StringEncryptor stringEncryptor;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<LoginOutputDTO> login(String register, LoginInputDTO loginInputDTO) {
        // 注册
        if (Constant.REGISTER_FLAG_TRUE.equals(register)) {
            QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("account_no", loginInputDTO.getAccountNo());
            // 已经注册过
            if (userInfoDao.selectOne(queryWrapper) != null) {
                return ResultData.fail(ReturnCodeEnum.USER_INFO_EXIST_ERR.getCode(),
                        ReturnCodeEnum.USER_INFO_EXIST_ERR.getMessage());
            }
            // 注册
            UserInfo userInfo = getUserInfo(loginInputDTO);
            if (userInfoDao.insert(userInfo) == 1) {
                LoginOutputDTO loginOutputDTO = Convert.convert(LoginOutputDTO.class, userInfo);
                return ResultData.success(loginOutputDTO);
            } else {
                return ResultData.fail(ReturnCodeEnum.USER_REGISTER_ERR.getCode(),
                        ReturnCodeEnum.USER_REGISTER_ERR.getMessage());
            }
        } else {
            // 登录
            QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
            wrapper.eq("account_no", loginInputDTO.getAccountNo());
            UserInfo userInfo = userInfoDao.selectOne(wrapper);
            if (userInfo == null) {
                return ResultData.fail(ReturnCodeEnum.USER_INFO_EMPTY.getCode(), ReturnCodeEnum.USER_INFO_EMPTY.getMessage());
            } else {
                String password = userInfo.getPassword();
                String decrypt = stringEncryptor.decrypt(password);
                return StrUtil.equals(decrypt, loginInputDTO.getPassword()) ?
                        ResultData.success(Convert.convert(LoginOutputDTO.class, userInfo))
                        : ResultData.fail(ReturnCodeEnum.PASSWORD_ERR.getCode(), ReturnCodeEnum.PASSWORD_ERR.getMessage());
            }
        }
    }

    private UserInfo getUserInfo(LoginInputDTO loginInputDTO) {
        UserInfo userInfo = Convert.convert(UserInfo.class, loginInputDTO);
        String password = stringEncryptor.encrypt(loginInputDTO.getPassword());
        PersonInfoSource personInfoSource = RandomSource.personInfoSource();
        Date date = new Date();
        String timestamp = String.valueOf(System.currentTimeMillis());
        String nickName = personInfoSource.randomNickName(5) + timestamp.substring(timestamp.length() - 5);
        return userInfo.setPassword(password)
                .setNickName(nickName)
                .setCreateDate(date)
                .setCreateTime(date);
    }
}
