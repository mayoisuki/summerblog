package com.panxudong.summerblog.service;

import com.panxudong.summerblog.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    String login(String username,String password);

    /**
     * 根据token查询登录用户
     * @param token
     * @return
     */
    User getCurrentUser(String token);


    /**
     * 注册
     * @param username
     * @param password
     * @param confirmPassword
     */
    void register(String username,String password,String confirmPassword);

    /**
     * 退出登录
     * @param token
     */
    void logout(String token);
}
