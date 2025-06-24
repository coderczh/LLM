package com.coderczh.backend.common.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderczh.backend.common.Constant;
import com.coderczh.backend.dao.UserInfoDao;
import com.coderczh.backend.dto.LoginInputDTO;
import com.coderczh.backend.entity.UserInfo;
import io.github.yindz.random.RandomSource;
import io.github.yindz.random.source.PersonInfoSource;
import jakarta.annotation.Resource;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CommonUtil {

    @Resource
    private StringEncryptor stringEncryptor;

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private RedisUtil redisUtil;

    public UserInfo getUserInfo(LoginInputDTO loginInputDTO) {
        UserInfo userInfo = Convert.convert(UserInfo.class, loginInputDTO);
        PersonInfoSource personInfoSource = RandomSource.personInfoSource();

/*        while (true) {
            String accountNo = StrUtil.isBlank(loginInputDTO.getAccountNo())
                    ? personInfoSource.randomNickName(10) : loginInputDTO.getAccountNo();
            List<Object> resList = redisUtil.getAllList(Constant.REDIS_KEY_USER_INFO);
            for (Object res : resList) {
                if(StrUtil.equals())
            }
        }*/



        String password = stringEncryptor.encrypt(loginInputDTO.getPassword());

        Date date = new Date();
        String timestamp = String.valueOf(System.currentTimeMillis());
        String nickName = personInfoSource.randomNickName(5) + timestamp.substring(timestamp.length() - 5);
        return userInfo.setAvatar(Constant.DEFAULT_AVATAR_URL)
                .setGender(Constant.DEFAULT_GENDER)
                .setPassword(password)
                .setNickName(nickName)
                .setCreateDate(date)
                .setCreateTime(date);
    }
}
