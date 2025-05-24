package com.coderczh.backend.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginOutputDTO {
    private String nickName;
    private String avatar;
    private String gender;
    private String accountNo;
    private String phoneNo;
    private String email;
}
