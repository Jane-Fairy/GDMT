package com.entity;

/**
 * 封装结果信息
 */
public class ResultBean<T> {

    private Integer code; //状态码
    private String msg; //消息
    private T data; //封装的数据

    public ResultBean() {}

    public ResultBean(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultBean(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
