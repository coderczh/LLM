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

    @PostMapping("/user-info")
    public ResultData<UserInfo> getUserInfo(@RequestBody UserInfo userInfo) {
        return loginService.selectUserInfo(userInfo);
    }

    @PutMapping("/user-info")
    public ResultData<Integer> alterUserInfo(@RequestBody UserInfo userInfo) {
        return loginService.updateUserInfo(userInfo);
    }
}
