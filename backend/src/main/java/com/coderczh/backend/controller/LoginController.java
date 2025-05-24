package com.coderczh.backend.controller;

import com.coderczh.backend.dto.LoginInputDTO;
import com.coderczh.backend.dto.LoginOutputDTO;
import com.coderczh.backend.resp.ResultData;
import com.coderczh.backend.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/account/{register}")
    public ResultData<LoginOutputDTO> getUserInfo(@PathVariable String register, @RequestBody LoginInputDTO loginInputDTO) {
        return loginService.loginByAccount(register, loginInputDTO);
    }

    @PostMapping("/verify-code")
    public ResultData<String> getVerifyCode(@RequestBody String phoneNo) {
        return loginService.getVerifyCode(phoneNo);
    }
}
