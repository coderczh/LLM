package com.coderczh.backend.service;

import com.coderczh.backend.dto.LoginInputDTO;
import com.coderczh.backend.dto.LoginOutputDTO;
import com.coderczh.backend.resp.ResultData;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface LoginService {

    ResultData<LoginOutputDTO> loginByAccount(String register, LoginInputDTO loginInputDTO);

    ResultData<String> getVerifyCode(@Valid String phoneNo);
}
