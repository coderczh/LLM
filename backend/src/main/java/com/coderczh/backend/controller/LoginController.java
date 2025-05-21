package com.coderczh.backend.controller;

import com.coderczh.backend.entity.UserInfo;
import com.coderczh.backend.resp.ResultData;
import com.coderczh.backend.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/account")
    public ResultData<UserInfo> getUserInfo(@RequestBody UserInfo userInfo) {
        System.out.println(userInfo);
        return null;
    }

    @PostMapping("/verify-code")
    public ResultData<String> getVerifyCode(@RequestBody String phoneNo) {
        return loginService.getVerifyCode(phoneNo);
    }
}
