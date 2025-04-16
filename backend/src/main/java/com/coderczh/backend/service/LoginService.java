package com.coderczh.backend.service;

import com.coderczh.backend.entity.UserInfo;
import com.coderczh.backend.resp.ResultData;

public interface LoginService {

    ResultData<UserInfo> getUserInfo(UserInfo userInfo);
}
