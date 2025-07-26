package com.coderczh.backend.service;

import com.coderczh.backend.dto.UserInfoInputDTO;
import com.coderczh.backend.dto.UserInfoOutputDTO;
import com.coderczh.backend.resp.ResultData;

public interface UserInfoService {
    ResultData<UserInfoOutputDTO> updateUserInfo(UserInfoInputDTO userInfoInputDTO);
}
