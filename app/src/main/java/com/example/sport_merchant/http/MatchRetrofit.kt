package com.example.sport_merchant.http

import com.example.base.http.RetrofitFactory
import com.example.sport_merchant.constant.Constant

/**
 * created by jump on 2020/8/23
 * describe: 赛事
 */
object MatchRetrofit : RetrofitFactory<MatchApi>() {
    override fun baseUrl(): String = Constant.MATCH_BASE_URL

    override fun getService(): Class<MatchApi> = MatchApi::class.java
}