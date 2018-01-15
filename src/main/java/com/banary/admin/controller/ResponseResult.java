package com.banary.admin.controller;

public class ResponseResult<T> {

    private String code;
    private String msg;
    private T data;

    public String getCode() {
        return code;
    }

    public ResponseResult setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseResult setData(T data) {
        this.data = data;
        return this;
    }
}
