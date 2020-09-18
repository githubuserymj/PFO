package com.offer.config;

import com.offer.config.interceptor.ErrorInterceptor;
import com.offer.config.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YMJ on 2019-09-26.
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer{
    @Autowired
    LoginInterceptor loginInterceptor;

    @Autowired
    ErrorInterceptor errorInterceptor;
    // 这个方法是用来配置静态资源的，比如html，js，css，等等
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    }

    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/html/**").excludePathPatterns(getExcludePathList());
        registry.addInterceptor(errorInterceptor).addPathPatterns("/**").excludePathPatterns(getExcludePathList());
    }
    //设置白名单
    public List<String> getExcludePathList(){
        List<String> excludePathList = new ArrayList<>();
        //不拦截静态资源文件
        excludePathList.add("/js/**");
        excludePathList.add("/css/**");
        excludePathList.add("/img/**");

        excludePathList.add("index.html");
        excludePathList.add("/html/user/login.html");
        excludePathList.add("/html/user/regist.html");
        return excludePathList;
    }



}
