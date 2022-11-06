package com.panxudong.summerblog.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.panxudong.summerblog.entity.Category;
import com.panxudong.summerblog.entity.User;
import com.panxudong.summerblog.service.CategoryService;
import com.panxudong.summerblog.service.UserService;
import com.panxudong.summerblog.vo.JsonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author kaza
 * @create 2022-10-28 19:27
 **/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController extends BaseController{

    private final CategoryService categoryService;

    /**
     * 查询单个分类
     * @param id
     * @return
     */
    @GetMapping("/getById")
    public JsonResult getById(@RequestParam Long id){
        return JsonResult.data(categoryService.getById(id));
    }

    /**
     * 分页查询分类
     * @param pageNum
     * @param pageSize
     * @param keywords
     * @return
     */
    @GetMapping("/page")
    public JsonResult page(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(defaultValue = "") String keywords){
        LambdaQueryWrapper<Category> queryWrapper=new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(keywords)){
            queryWrapper.like(Category::getName,keywords);
        }
        IPage<Category> page = categoryService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return JsonResult.data(page);
    }


    /**
     * 添加或保存
     * @param category
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public JsonResult saveOrUpdate(@RequestBody Category category){

        User currentUser = getCurrentUser();
        if (category.getId()!=null){
//            更新
            Category dbCategory = categoryService.getById(category.getId());
            if (dbCategory==null){
                return JsonResult.error("更新的分类不存在");
            }

            if (!Objects.equals(dbCategory.getUserId(),currentUser.getId())){
                return JsonResult.error("更新的分类不属于当前用户");
            }
        }else {
//            添加
            category.setUserId(getCurrentUser().getId());
            category.setCreateTime(new Date());
        }

        categoryService.saveOrUpdate(category);
        return JsonResult.success();
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @PostMapping("/removeById")
    public JsonResult removeById(@RequestParam Long id){
        categoryService.removeById(id);
        return JsonResult.success();
    }


    /**
     * 分类树
     * @return
     */
    @GetMapping("/tree")
    public JsonResult tree(){
        User currentUser = getCurrentUser();
        LambdaQueryWrapper<Category> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getUserId,currentUser.getId());
        List<Category> categoryList = categoryService.list(queryWrapper);

        TreeNodeConfig config=new TreeNodeConfig();
        config.setIdKey("id");
        config.setParentIdKey("pid");
        config.setWeightKey("createTime");

        List<Tree<Long>> treeList = TreeUtil.build(categoryList, 0L, config, (object, tree) -> {
            tree.putExtra("id", object.getId());
            tree.putExtra("value", object.getId());
            tree.putExtra("pid", object.getPid());
            tree.putExtra("label", object.getName());
        });
        return JsonResult.data(treeList);
    }

}
