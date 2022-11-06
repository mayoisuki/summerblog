package com.panxudong.summerblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 文章
 * @TableName article
 */
@TableName(value ="article")
@Data
public class Article implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//前台传的时间是字符串，可以用这个注解转成Date
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//返回json格式化时间
    private Date createTime;

    /**
     * 作者昵称
     */
    @TableField(exist = false)
    private String userNickName;

    /**
     * 分类ids
     */
    @TableField(exist = false)
    private List<Long> categoryIds;

    /**
     * 文章所属分类的列表
     */
    @TableField(exist = false)
    private List<Category> categoryList;


    @TableField(exist = false)
    private User user;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}