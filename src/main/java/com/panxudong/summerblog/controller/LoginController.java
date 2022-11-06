package com.panxudong.summerblog.controller;

import com.panxudong.summerblog.entity.User;
import com.panxudong.summerblog.service.UserService;
import com.panxudong.summerblog.util.RedisUtil;
import com.panxudong.summerblog.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author kaza
 * @create 2022-10-28 20:17
 **/
@RestController
@RequestMapping("/api")
public class LoginController extends BaseController{



    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public JsonResult login(@RequestParam String username,
                            @RequestParam String password) {
        String token = userService.login(username, password);
        return JsonResult.data(token);
    }

    /**
     * 获取登录用户
     *
     * @param token
     * @return
     */
    @GetMapping("/getCurrentUser")
    public JsonResult getCurrentUser(@RequestParam String token) {
        User currentUser = userService.getCurrentUser(token);
        return JsonResult.data(currentUser);
    }

    /**
     * 注册
     * @param username
     * @param password
     * @param confirmPassword
     * @return
     */
    @PostMapping("/register")
    public JsonResult register(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String confirmPassword) {
        userService.register(username, password, confirmPassword);
        return JsonResult.success();
    }


    /**
     * 退出登录
     * @param token
     * @return
     */
    @GetMapping("/logout")
    public JsonResult logout(@RequestParam String token) {
        userService.logout(token);
        return JsonResult.success();
    }


}
