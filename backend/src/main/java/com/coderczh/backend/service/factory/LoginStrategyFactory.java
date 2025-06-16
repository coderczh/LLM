package com.coderczh.backend.service.factory;

import com.coderczh.backend.service.strategy.LoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LoginStrategyFactory {

    private final Map<String, LoginStrategy> loginStrategyMap;

    @Autowired
    public LoginStrategyFactory(Map<String, LoginStrategy> loginStrategyMap) {
        this.loginStrategyMap = loginStrategyMap;
    }

    public LoginStrategy getLoginStrategy(String loginType) {
        LoginStrategy loginStrategy = loginStrategyMap.get(loginType);
        if (loginStrategy == null) {
            throw new RuntimeException("登录方式错误, loginType: " + loginType);
        }
        return loginStrategy;
    }
}
