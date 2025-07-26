package com.coderczh.backend.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.coderczh.backend.common.util.RedisUtil;
import com.coderczh.backend.dao.UserInfoDao;
import com.coderczh.backend.dto.UserInfoInputDTO;
import com.coderczh.backend.dto.UserInfoOutputDTO;
import com.coderczh.backend.entity.UserInfo;
import com.coderczh.backend.resp.ResultData;
import com.coderczh.backend.resp.ReturnCodeEnum;
import com.coderczh.backend.service.UserInfoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户信息服务实现类
 *
 * @author coderczh
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private RedisUtil redisUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<UserInfoOutputDTO> updateUserInfo(UserInfoInputDTO userInfoInputDTO) {
        String verifyCode = userInfoInputDTO.getVerifyCode();
        if (!StrUtil.equals((String) redisUtil.getStr(userInfoInputDTO.getPhoneNo()), verifyCode)) {
            return ResultData.fail(ReturnCodeEnum.VERIFY_CODE_ERR.getCode(),
                    ReturnCodeEnum.VERIFY_CODE_ERR.getMessage());
        }
        UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("nick_name", userInfoInputDTO.getNickName())
                .set("phone_no", userInfoInputDTO.getPhoneNo())
                .eq("account_no", userInfoInputDTO.getAccountNo());
        int updateRes = userInfoDao.update(updateWrapper);
        if (updateRes == 1) {
            UserInfoOutputDTO userInfoOutputDTO = Convert.convert(UserInfoOutputDTO.class, userInfoInputDTO);
            return ResultData.success(userInfoOutputDTO);
        } else {
            return ResultData.fail(ReturnCodeEnum.USER_INFO_UPDATE_ERR.getCode(),
                    ReturnCodeEnum.USER_INFO_UPDATE_ERR.getMessage());
        }
    }
}
