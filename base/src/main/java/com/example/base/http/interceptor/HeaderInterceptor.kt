package com.example.base.http.interceptor

import com.example.base.http.constant.HttpConstant
import com.example.base.utils.Preference
import com.example.base.utils.SPStaticUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * created by jump on 2020/8/21
 * describe:
 */
class HeaderInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val builder = request.newBuilder()

        builder.addHeader("Content-type", "application/json")
            .header("requestId",HttpConstant.TOKEN)
//            .header("requestId", SPStaticUtils.getString(HttpConstant.TOKEN_KEY))

        return chain.proceed(builder.build())
    }

}