package com.panxudong.summerblog.config;

import com.panxudong.summerblog.interceptor.UserInterceptor;
import com.panxudong.summerblog.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author kaza
 * @create 2022-11-02 10:07
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private UserInterceptor userInterceptor;

    public static String[] excludePathPatterns={"/api/login","/api/register","/api/logout","/api/getCurrentUser"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:///"+FileUtil.UPLOADS_PATH);
    }

    /**
     * 添加拦截器，拦截路径为“/api/**”,登录和注册的就不拦截
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns(excludePathPatterns);
    }
}
