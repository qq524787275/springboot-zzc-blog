package com.zhuzichu.blog.core.result;

import com.alibaba.fastjson.JSON;

/**
 * 统一API响应结果封装
 */
public class Result {
    private int code;
    private String message;
    private Object data;

    public Result(){
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message=resultCode.getMessage();
    }

    public Result(ResultCode resultCode,Object data) {
        this.code = resultCode.getCode();
        this.message=resultCode.getMessage();
        this.data=data;
    }




    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message=resultCode.getMessage();
        return this;
    }

    public int getStatus() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getResult() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
