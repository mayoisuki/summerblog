package com.panxudong.summerblog.controller;

import com.panxudong.summerblog.exception.BlogException;
import com.panxudong.summerblog.vo.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kaza
 * @create 2022-10-28 10:56
 **/
@RestController
public class DemoController extends BaseController{


    @GetMapping("/demo")
    public JsonResult demo(){
//        throw new BlogException("测试异常信息");
        return JsonResult.data("我是测试数据");
    }
}
