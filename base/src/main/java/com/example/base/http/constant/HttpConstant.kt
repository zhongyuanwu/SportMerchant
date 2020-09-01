package com.example.base.http.constant

object HttpConstant {

    const val DEFAULT_TIMEOUT: Long = 15

    const val MAX_CACHE_SIZE: Long = 1024 * 1024 * 50 // 50M 的缓存大小

    var TOKEN = ""
    var DOMAIN=""
    const val SET_COOKIE_KEY = "set-cookie"
    const val COOKIE_NAME = "Cookie"

    //请求成功状态码
    const val SUCCESS_CODE = "0000000"

    //token 过期状态码
    const val LOCAL_TOKEN_INVALID_CODE = "0401013"

}