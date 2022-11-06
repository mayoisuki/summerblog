package com.panxudong.summerblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.panxudong.summerblog.entity.User;
import com.panxudong.summerblog.service.UserService;
import com.panxudong.summerblog.vo.JsonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 个人信息
 * @author kaza
 * @create 2022-10-28 19:27
 **/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/profile")
public class ProfileController extends BaseController{

    private final UserService userService;

    /**
     * 保存
     * @param user
     * @return
     */
    @PostMapping("/save")
    public JsonResult save(@RequestBody User user){
        userService.updateById(user);
        return JsonResult.success();
    }



}
