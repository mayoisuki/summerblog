package com.panxudong.summerblog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panxudong.summerblog.entity.Article;
import com.panxudong.summerblog.service.ArticleService;
import com.panxudong.summerblog.mapper.ArticleMapper;
import com.sun.org.glassfish.external.amx.AMX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public IPage<Article> findByCategoryId(Long categoryId, String keywords, Page<Article> page) {
        return page.setRecords(articleMapper.findByCategoryId(categoryId,keywords,page));
    }
}




