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

    @PostMapping("/{loginType}/{register}")
    public ResultData<LoginOutputDTO> getUserInfo(@PathVariable("loginType") String loginType,
                                                  @PathVariable("register") String register,
                                                  @RequestBody LoginInputDTO loginInputDTO) {
        return loginService.loginByAccount(loginType, register, loginInputDTO);
    }

    @PostMapping("/verify-code")
    public ResultData<String> getVerifyCode(@RequestBody String phoneNo) {
        return loginService.getVerifyCode(phoneNo);
    }
}
