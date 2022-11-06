package com.panxudong.summerblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.panxudong.summerblog.entity.Article;
import com.panxudong.summerblog.entity.ArticleCategoryRel;
import com.panxudong.summerblog.entity.Category;
import com.panxudong.summerblog.entity.User;
import com.panxudong.summerblog.service.ArticleCategoryRelService;
import com.panxudong.summerblog.service.ArticleService;
import com.panxudong.summerblog.service.CategoryService;
import com.panxudong.summerblog.service.UserService;
import com.panxudong.summerblog.vo.JsonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author kaza
 * @create 2022-10-28 19:27
 **/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/article")
public class ArticleController extends BaseController {

    private final ArticleService articleService;

    private final CategoryService categoryService;

    private final UserService userService;

    private final ArticleCategoryRelService articleCategoryRelService;


    /**
     * 查询单个文章
     *
     * @param id
     * @return
     */
    @GetMapping("/getById")
    public JsonResult getById(@RequestParam Long id) {
        Article article = articleService.getById(id);
        if (article==null){
            return JsonResult.error("文章不存在");
        }

//        查询分类
        LambdaQueryWrapper<ArticleCategoryRel> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleCategoryRel::getArticleId,id).orderByAsc(ArticleCategoryRel::getSort);
        List<ArticleCategoryRel> articleCategoryRelList = articleCategoryRelService.list(queryWrapper);
        List<Long> categoryIds = articleCategoryRelList.stream().map(ArticleCategoryRel::getCategoryId).collect(Collectors.toList());
        article.setCategoryIds(categoryIds);

        List<Category> categoryList=new ArrayList<>();
        for (Long categoryId : categoryIds) {
            Category category = categoryService.getById(categoryId);
            categoryList.add(category);
        }
        article.setCategoryList(categoryList);

        return JsonResult.data(article);
    }

    /**
     * 分页查询文章
     *
     * @param pageNum
     * @param pageSize
     * @param keywords
     * @return
     */
    @GetMapping("/page")
    public JsonResult page(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(defaultValue = "") String keywords,
                           @RequestParam(defaultValue = "-1") Long categoryId) {

        Page<Article> page = new Page<>(pageNum, pageSize);
        IPage<Article> pageResult = null;
        if (categoryId!=-1){
//            如果categoryId不等于-1，说明有传categoryId，查询分类id=categoryId的文章
            pageResult= articleService.findByCategoryId(categoryId,keywords,page);
        }else {
            LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
            if (!StringUtils.isEmpty(keywords)) {
                queryWrapper.like(Article::getTitle, keywords).or().like(Article::getContent, keywords);
            }
            pageResult = articleService.page(page, queryWrapper);
            for (Article article : pageResult.getRecords()) {
                User user = userService.getById(article.getUserId());
                article.setUserNickName(user != null ? user.getNickname() : "");
            }
        }

        return JsonResult.data(pageResult);
    }


    /**
     * 添加或保存
     *
     * @param article
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public JsonResult saveOrUpdate(@RequestBody Article article) {

        User currentUser = getCurrentUser();
        if (article.getId()!=null){
//            更新
            Article dbArticle = articleService.getById(article.getId());
            if (dbArticle==null){
                return JsonResult.error("文章不存在");
            }
            if (!Objects.equals(currentUser.getId(),dbArticle.getUserId())){
                return JsonResult.error("没有权限");
            }
        }else {
//            添加
            article.setUserId(currentUser.getId());
            article.setCreateTime(new Date());
        }

        articleService.saveOrUpdate(article);

//        先删除文章分类关联
        LambdaQueryWrapper<ArticleCategoryRel> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleCategoryRel::getArticleId,article.getId());
        articleCategoryRelService.remove(queryWrapper);

//        再添加文章分类关联
        if (article.getCategoryIds()!=null&&article.getCategoryIds().size()>0){
            int count=0;
            for (Long categoryId : article.getCategoryIds()) {
                ArticleCategoryRel articleCategoryRel=new ArticleCategoryRel();
                articleCategoryRel.setArticleId(article.getId());
                articleCategoryRel.setCategoryId(categoryId);
                articleCategoryRel.setSort(++count);
                articleCategoryRelService.save(articleCategoryRel);
            }
        }

        return JsonResult.success();
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    @PostMapping("/removeById")
    public JsonResult removeById(@RequestParam Long id) {
        articleService.removeById(id);
        return JsonResult.success();
    }

}
