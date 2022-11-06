package com.panxudong.summerblog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.panxudong.summerblog.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface ArticleService extends IService<Article> {

    IPage<Article> findByCategoryId(Long categoryId, String keywords, Page<Article> page);
}
