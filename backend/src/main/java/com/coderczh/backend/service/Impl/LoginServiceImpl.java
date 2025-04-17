package com.coderczh.backend.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
    public ResultData<UserInfo> selectUserInfo(UserInfo userInfo) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("job_no", userInfo.getJobNo())
                .eq("password", userInfo.getPassword());
        UserInfo queryRes = userInfoDao.selectOne(queryWrapper);
        if (queryRes == null) {
            return ResultData.fail(ReturnCodeEnum.USER_INFO_ERROR.getCode(),
                    ReturnCodeEnum.USER_INFO_ERROR.getMessage());
        }
        return ResultData.success(queryRes);
    }

    @Override
    public ResultData<Integer> updateUserInfo(UserInfo userInfo) {
        UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("job_no", userInfo.getJobNo());
        updateWrapper.set("password", userInfo.getPassword())
                .set("nick_name", userInfo.getNickName())
                .set("gender", userInfo.getGender());
        int updateRes = userInfoDao.update(updateWrapper);
        if (updateRes != 1) {
            return ResultData.fail(ReturnCodeEnum.UPDATE_USER_INFO_FAIL.getCode(),
                    ReturnCodeEnum.UPDATE_USER_INFO_FAIL.getMessage());
        }
        return ResultData.success(updateRes);
    }
}
