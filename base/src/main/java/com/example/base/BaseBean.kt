package com.example.base

/**
 * created by jump on 2020/8/30
 * describe:
 */
class BaseBean<T> {
    var code: String = ""
    var msg: String = ""
    var data: T? = null
    var ts: Long = 0
    var pagedata: String? = null
    var status = false

    override fun toString(): String {
        return "BaseEntity{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", serverTime=" + ts +
                ", status=" + status +
                '}'
    }
}