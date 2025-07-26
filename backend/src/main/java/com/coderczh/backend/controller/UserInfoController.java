package com.coderczh.backend.controller;

import com.coderczh.backend.dto.UserInfoInputDTO;
import com.coderczh.backend.dto.UserInfoOutputDTO;
import com.coderczh.backend.resp.ResultData;
import com.coderczh.backend.service.UserInfoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-info")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @PatchMapping
    public ResultData<UserInfoOutputDTO> updateUserInfo(@RequestBody UserInfoInputDTO userInfoInputDTO) {
        return userInfoService.updateUserInfo(userInfoInputDTO);
    }
}
