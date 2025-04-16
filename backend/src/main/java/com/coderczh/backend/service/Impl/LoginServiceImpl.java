package com.coderczh.backend.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderczh.backend.dao.UserInfoDao;
import com.coderczh.backend.entity.UserInfo;
import com.coderczh.backend.resp.ResultData;
import com.coderczh.backend.resp.ReturnCodeEnum;
import com.coderczh.backend.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public ResultData<UserInfo> getUserInfo(UserInfo userInfo) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userInfo.getId())
                .eq("password", userInfo.getPassword());
        if (userInfoDao.selectOne(queryWrapper) == null) {
            return ResultData.fail(ReturnCodeEnum.USER_INFO_ERROR.getCode(),
                    ReturnCodeEnum.USER_INFO_ERROR.getMessage());
        }
        return null;
    }
}
