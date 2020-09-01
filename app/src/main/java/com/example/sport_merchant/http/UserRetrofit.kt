package com.example.sport_merchant.http

import com.example.base.http.RetrofitFactory
import com.example.sport_merchant.constant.Constant

/**
 * created by jump on 2020/8/21
 * describe: 用户
 */
object UserRetrofit : RetrofitFactory<UserApi>() {

    override fun baseUrl(): String = Constant.USER_BASE_URL

    override fun getService(): Class<UserApi> = UserApi::class.java

}