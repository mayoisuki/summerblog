package com.panxudong.summerblog.exception;

import com.panxudong.summerblog.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 捕获异常，返回异常信息
 * @author kaza
 * @create 2022-10-28 11:08
 **/
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {


    @ExceptionHandler(BlogException.class)
    @ResponseBody
    public JsonResult processBlogException(BlogException e){
        log.error("业务异常："+e);
        return JsonResult.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult processException(Exception e){
        log.error("系统异常："+e);
        return JsonResult.error(e.getMessage());
    }
}
