package com.panxudong.summerblog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panxudong.summerblog.entity.User;
import com.panxudong.summerblog.exception.BlogException;
import com.panxudong.summerblog.service.UserService;
import com.panxudong.summerblog.mapper.UserMapper;
import com.panxudong.summerblog.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 *
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{


    @Autowired
    private RedisUtil redisUtil;

    /**
     * 根据用户名获得用户
     * @param username
     * @return
     */
    private User getUserByUsername(String username){
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        User user = getOne(queryWrapper);
        return user;
    }
    @Override
    public String login(String username, String password) {

//        判断用户名
        User user = getUserByUsername(username);
        if (user==null){
            log.error("用户名不存在：{}",username);
            throw new BlogException("用户名不存在");
        }

//        判断密码
        if (!Objects.equals(user.getPassword(),password)){
            log.error("密码错误：username:{}",username);
            throw new BlogException("密码错误");
        }

//        生成token，存储token和用户对象的关系
        String token = UUID.randomUUID().toString().replaceAll("-", "");
//        把用户id存进redis
        redisUtil.set("token:"+token, String.valueOf(user.getId()),60*30);
        return token;
    }

    @Override
    public User getCurrentUser(String token) {
//        根据token查询
        String value = redisUtil.get("token:" + token);
        if (StringUtils.isEmpty(value)){
            return null;
        }
//        由于放在redis的用户数据可能过时，重新从数据库中取数据返回
        return getById(Long.valueOf(value));
    }

    @Override
    public void register(String username, String password, String confirmPassword) {
//        判断两次密码是否一致
        if (!Objects.equals(password,confirmPassword)){
            log.error("两次密码不一致：username:{}",username);
            throw new BlogException("两次密码不一致");
        }
//        判断用户名长度
        if (StringUtils.isEmpty(username)||username.length()<4||username.length()>20){
            log.error("用户名长度不合法：username:{}",username);
            throw new BlogException("用户名长度必须为4-20位");
        }
//        判断密码长度
        if (StringUtils.isEmpty(password)||password.length()<4||password.length()>20){
            log.error("密码长度不合法：username:{}",username);
            throw new BlogException("密码长度必须为4-20位");
        }
//        判断用户名是否存在
        User user = getUserByUsername(username);
        if (user!=null){
            log.error("用户名已存在：username:{}",username);
            throw new BlogException("用户名已存在");
        }
//        新增用户
        user=new User();
        user.setUsername(username);
        user.setNickname(username);
        user.setPassword(password);
        user.setCreateTime(new Date());
        user.setAvatar("/uploads/default.png");
        save(user);
    }

    @Override
    public void logout(String token) {
        redisUtil.del("token:"+token);
    }
}




