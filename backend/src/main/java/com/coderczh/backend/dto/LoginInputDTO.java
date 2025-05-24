package com.coderczh.backend.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class LoginInputDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 8030322258544928111L;
    private String accountNo;
    private String password;
    private String phoneNo;
    private String verifyCode;
}
