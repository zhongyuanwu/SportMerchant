package com.example.base;

/**
 * 创建时间：2019/8/28
 * 编写人：Averson
 * 功能描述：
 */

public class BaseEntity<T> {
    /**
     * 响应code
     */
    public String code;

    /**
     * 响应消息
     */
    public String msg;

    /**
     * 数据
     */
    public T data;

    public long ts;

    public String pagedata;

    private boolean status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", serverTime=" + ts +
                ", status=" + status +
                '}';
    }
}
