package com.coderczh.backend.service;

import com.coderczh.backend.dto.LoginInputDTO;
import com.coderczh.backend.dto.LoginOutputDTO;
import com.coderczh.backend.resp.ResultData;

public interface LoginService {

    ResultData<LoginOutputDTO> loginByAccount(String loginType, String register, LoginInputDTO loginInputDTO);

    ResultData<String> getVerifyCode(String phoneNo);
}
