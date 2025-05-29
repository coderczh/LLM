package com.coderczh.backend.service.Impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderczh.backend.common.util.RedisUtil;
import com.coderczh.backend.dao.UserInfoDao;
import com.coderczh.backend.dto.LoginInputDTO;
import com.coderczh.backend.dto.LoginOutputDTO;
import com.coderczh.backend.entity.UserInfo;
import com.coderczh.backend.resp.ResultData;
import com.coderczh.backend.resp.ReturnCodeEnum;
import com.coderczh.backend.service.LoginService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private RedisUtil redisUtil;

    private static final SymmetricCrypto SM4 = new SymmetricCrypto(SymmetricAlgorithm.PBEWithSHA1AndDESede);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<LoginOutputDTO> loginByAccount(String register, LoginInputDTO loginInputDTO) {
        String notRegister = "1";
        // 登录
        if (notRegister.equals(register)) {
            QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
            String password = SM4.decryptStr(loginInputDTO.getPassword());
            wrapper.eq("account_no", loginInputDTO.getAccountNo())
                    .eq("password", password);
            UserInfo userInfo = userInfoDao.selectOne(wrapper);
            if (userInfo == null) {
                return ResultData.fail(ReturnCodeEnum.USER_INFO_ERR.getCode(), ReturnCodeEnum.USER_INFO_ERR.getMessage());
            } else {
                return ResultData.success(Convert.convert(LoginOutputDTO.class, userInfo));
            }
        } else {
            // 注册
            int registerCount = 1;
            String password = SM4.encryptHex(loginInputDTO.getPassword());
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
