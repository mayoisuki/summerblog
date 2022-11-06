package com.panxudong.summerblog.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.panxudong.summerblog.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity com.panxudong.summerblog.entity.Article
 */
public interface ArticleMapper extends BaseMapper<Article> {
    /**
     * 根据分类id查询
     * @param categoryId
     * @param keywords
     * @param page
     * @return
     */
    List<Article> findByCategoryId(Long categoryId, String keywords, Page<Article> page);

}




