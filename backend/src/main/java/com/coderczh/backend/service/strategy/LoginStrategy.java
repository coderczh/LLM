package com.coderczh.backend.service.strategy;

import com.coderczh.backend.dto.LoginInputDTO;
import com.coderczh.backend.dto.LoginOutputDTO;
import com.coderczh.backend.resp.ResultData;

public interface LoginStrategy {
    ResultData<LoginOutputDTO> login(String register, LoginInputDTO loginInputDTO);
}
