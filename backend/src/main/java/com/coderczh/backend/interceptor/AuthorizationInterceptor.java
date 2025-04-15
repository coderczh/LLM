package com.coderczh.backend.interceptor;

import com.coderczh.backend.common.Constant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@SuppressWarnings("all")
public class AuthorizationInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authorization = request.getHeader("Authorization");
        if (!Constant.API_KEY.equals(authorization)) {
            throw new RuntimeException("请求头API_KEY验证失败，请求中的API_KEY为：" + authorization);
        } else {
            return true;
        }
    }
}
