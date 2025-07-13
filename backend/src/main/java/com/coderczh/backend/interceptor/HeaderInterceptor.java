package com.coderczh.backend.interceptor;

import cn.hutool.core.util.StrUtil;
import com.coderczh.backend.common.util.RedisUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@SuppressWarnings("all")
public class HeaderInterceptor implements HandlerInterceptor {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        if (StrUtil.isBlank((String) redisUtil.getStr(authorization))) {
            log.info("请求头校验值不存在");
            throw new RuntimeException("请求头校验值不存在");
        }
        return true;
    }
}
