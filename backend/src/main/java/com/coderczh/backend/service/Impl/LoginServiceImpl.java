package com.coderczh.backend.service.Impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.coderczh.backend.common.util.RedisUtil;
import com.coderczh.backend.dao.UserInfoDao;
import com.coderczh.backend.entity.UserInfo;
import com.coderczh.backend.resp.ResultData;
import com.coderczh.backend.resp.ReturnCodeEnum;
import com.coderczh.backend.service.LoginService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public ResultData<String> getVerifyCode(@Valid String phoneNo) {
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
