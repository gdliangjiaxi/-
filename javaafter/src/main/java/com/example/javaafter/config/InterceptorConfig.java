package com.example.javaafter.config;

import com.example.javaafter.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")//拦截  (其他接口token验证)
                .excludePathPatterns("/login/**")
                .excludePathPatterns("/files/**");//所有用户都放行,放行静态资源(登录接口不放行,无法进行登录)

    }
}
