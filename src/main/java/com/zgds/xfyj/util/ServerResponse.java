package com.zgds.xfyj.util;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * 该统一响应类使用说明：
 * 前台如需后台返回约定的状态码，使用
 * 成功：createBySuccessMessageSpecialResponse
 * 失败：createByErrorMessageSpecialResponse
 * 其中泛型T是SpecialResponse.java
 * <p>
 * 除此种情况外，其他的使用不变
 * <p>
 * Created by ASUS-LXP on 2019/10/29.
 */

/* 值为空的属性不会被序列化 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {
    private int status;//后台响应：0--后台响应成功 1--后台响应失败
    private String msg;
    private T data;

    private ServerResponse() {
    }

    private ServerResponse(Integer status) {
        this.status = status;
    }

    private ServerResponse(Integer status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;

    }

    private ServerResponse(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    /*不需要出现在json序列化中*/
    @JsonIgnore
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMessages(String msg) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ServerResponse<T> createByError() {
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), errorMessage);
    }

    /**
     * @param errorMessage 错误信息
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createByErrorMessageSpecialResponse(String errorMessage, T data) {//errorMessage--给前台的提示  T--SpecialResponse
        return new ServerResponse<T>(1, errorMessage, data);
    }

    /**
     * @param message 成功信息
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createBySuccessMessageSpecialResponse(String message, T data) {
        return new ServerResponse<T>(0, message, data);
    }

    /**
     * 服务端异常响应
     *
     * @return
     */
    public static ServerResponse createServerEceptionResponse() {
        return ServerResponse.createByErrorMessage("服务器异常！");
    }

}
