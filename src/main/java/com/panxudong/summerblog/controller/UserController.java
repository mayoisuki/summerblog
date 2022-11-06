package com.panxudong.summerblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.panxudong.summerblog.entity.User;
import com.panxudong.summerblog.service.UserService;
import com.panxudong.summerblog.vo.JsonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author kaza
 * @create 2022-10-28 19:27
 **/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController{

    private final UserService userService;

    /**
     * 查询单个用户
     * @param id
     * @return
     */
    @GetMapping("/getById")
    public JsonResult getById(@RequestParam Long id){
        return JsonResult.data(userService.getById(id));
    }

    /**
     * 分页查询用户
     * @param pageNum
     * @param pageSize
     * @param keywords
     * @return
     */
    @GetMapping("/page")
    public JsonResult page(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(defaultValue = "") String keywords){
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(keywords)){
            queryWrapper.like(User::getUsername,keywords).or().like(User::getNickname,keywords);
        }
        IPage<User> page = userService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return JsonResult.data(page);
    }


    /**
     * 添加或保存
     * @param user
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public JsonResult saveOrUpdate(@RequestBody User user){
        userService.saveOrUpdate(user);
        return JsonResult.success();
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @PostMapping("/removeById")
    public JsonResult removeById(@RequestParam Long id){
        userService.removeById(id);
        return JsonResult.success();
    }

}
