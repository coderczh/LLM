package com.coderczh.backend.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserInfoOutputDTO {
    private String accountNo;
    private String avatar;
    private String nickName;
    private String phoneNo;
}
