package com.panxudong.summerblog.controller;

import com.panxudong.summerblog.entity.User;
import com.panxudong.summerblog.exception.BlogException;
import com.panxudong.summerblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kaza
 * @create 2022-10-31 10:03
 **/
public class BaseController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    /**
     * 获取登录用户
     * @return
     */
    public User getCurrentUser(){
        String token = request.getHeader("X-Token");
        User currentUser = userService.getCurrentUser(token);
        if (currentUser==null){
            throw new BlogException("请先登录");
        }
        return currentUser;
    }
}
