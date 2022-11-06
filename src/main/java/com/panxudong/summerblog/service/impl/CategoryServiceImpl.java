package com.panxudong.summerblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panxudong.summerblog.entity.Category;
import com.panxudong.summerblog.service.CategoryService;
import com.panxudong.summerblog.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




