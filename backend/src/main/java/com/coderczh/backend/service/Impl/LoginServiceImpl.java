package com.coderczh.backend.service.Impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
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
import org.jasypt.encryption.StringEncryptor;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<LoginOutputDTO> loginByAccount(String register, LoginInputDTO loginInputDTO) {
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
