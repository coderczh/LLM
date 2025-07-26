package com.coderczh.backend.dto;

import lombok.Data;

@Data
public class UserInfoInputDTO {
    private String accountNo;
    private String avatar;
    private String nickName;
    private String phoneNo;
    private String verifyCode;
}
