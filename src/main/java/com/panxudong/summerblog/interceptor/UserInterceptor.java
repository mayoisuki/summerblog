package com.panxudong.summerblog.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.panxudong.summerblog.entity.User;
import com.panxudong.summerblog.exception.BlogException;
import com.panxudong.summerblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kaza
 * @create 2022-11-04 9:46
 **/
//要加上@component让spring管理，里面的userService才能被spring注入
@Component
@Slf4j
public class UserInterceptor implements HandlerInterceptor {


    @Autowired
    private UserService userService;

    /**
     * 从header或地址栏获取token，判断有没有登录
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("URL            : {}", request.getRequestURL().toString());
//
        String token = request.getHeader("X-Token");
        if (StringUtils.isEmpty(token)){
            token=request.getParameter("X-Token");
        }

        User currentUser = userService.getCurrentUser(token);
        if (currentUser==null){
            throw new BlogException("请先登录");
        }

//        如果操作用户接口，判断是否有权限，可以考虑单独抽出来做权限控制，上shiro啥的
        String url = request.getRequestURI();
        if (url.startsWith("/api/user") && !"admin".equals(currentUser.getRole())){
            throw new BlogException("没有权限操作");
        }

        return true;
    }
}
