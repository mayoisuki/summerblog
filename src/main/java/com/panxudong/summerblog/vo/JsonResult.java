package com.panxudong.summerblog.vo;

import lombok.Data;

/**
 * @author kaza
 * @create 2022-10-28 10:47
 **/
@Data
public class JsonResult {

    /**
     * 返回的状态码，0：失败，1：成功
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private Object data;

    /**
     * 不返回数据的构造方法
     *
     * @param code 状态码
     * @param msg  信息
     */
    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 返回状态码和数据
     *
     * @param code 状态码
     * @param data 数据
     */
    public JsonResult(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    /**
     * 返回数据的构造方法
     *
     * @param code 状态码
     * @param msg  信息
     * @param data 数据
     */
    public JsonResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult error(String msg) {
        return new JsonResult(0, msg);
    }

    public static JsonResult error(String msg, Object data) {
        return new JsonResult(0, msg, data);
    }

    public static JsonResult success() {
        return new JsonResult(1, "操作成功");
    }

    public static JsonResult success(String msg) {
        return new JsonResult(1, msg);
    }

    public static JsonResult success(String msg, Object data) {
        return new JsonResult(1, msg, data);
    }

    public static JsonResult data(Object data) {
        return new JsonResult(1, "操作成功", data);
    }

}
