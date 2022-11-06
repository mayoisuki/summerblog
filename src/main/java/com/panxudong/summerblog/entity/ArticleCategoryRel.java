package com.panxudong.summerblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 文章分类关联
 * @TableName article_category_rel
 */
@TableName(value ="article_category_rel")
@Data
public class ArticleCategoryRel implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 排序号，越小越靠前
     */
    private Integer sort;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}