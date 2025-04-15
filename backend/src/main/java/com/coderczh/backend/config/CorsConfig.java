package com.coderczh.backend.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Slf4j
@SpringBootConfiguration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);   // 允许携带认证信息
        config.addAllowedOrigin("http://localhost:5173");   // 允许的域名
        config.addAllowedHeader("*");   // 允许所有请求头
        config.addAllowedMethod("*");   // 允许所有请求方法
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        log.info("=== CORS初始化完成 ===");
        return new CorsFilter(source);
    }
}
