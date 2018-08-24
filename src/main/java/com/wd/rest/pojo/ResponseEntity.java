package com.wd.rest.pojo;

import io.swagger.annotations.ApiModelProperty;

public class ResponseEntity<T> {

    @ApiModelProperty(name="code", value = "操作状态,0为成功")
    int code = 0;

    @ApiModelProperty(name="data", value = "结果集")
    T data;

    @ApiModelProperty(name="message", value = "操作状态不为0时,该值为错误信息")
    String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
