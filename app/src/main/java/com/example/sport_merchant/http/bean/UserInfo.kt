package com.example.sport_merchant.http.bean

/**
 * created by jump on 2020/8/22
 * describe:
 */
data class UserInfo(val userId: String,
                    val token: String,
                    var balance: Double,
                    val username: String,
                    val createTime:Long
)