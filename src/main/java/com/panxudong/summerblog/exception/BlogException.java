package com.panxudong.summerblog.exception;

/**
 * 自定义异常
 * @author kaza
 * @create 2022-10-28 11:05
 **/
public class BlogException extends RuntimeException {

    public BlogException(String message) {
        super(message);
    }

}
