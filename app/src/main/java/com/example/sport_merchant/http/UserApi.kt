package com.example.sport_merchant.http

import com.example.base.BaseEntity
import com.example.sport_merchant.http.bean.DomainBean
import com.example.sport_merchant.http.bean.UserInfo
import com.example.sport_merchant.http.request.LoginRequest
import io.reactivex.Observable
import retrofit2.http.*

/**
 * created by jump on 2020/8/21
 * describe:
 */
interface UserApi {
    /**
     * 登录获取token
     */
    @POST("user/login")
    fun login(@Body params: LoginRequest): Observable<BaseEntity<UserInfo>>

    /**
     * token 查询用户信息
     */
    @GET("getUserInfo")
    fun getUserInfo(@Query("token") token: String): Observable<BaseEntity<UserInfo>>

    /**
     * 查询玩家余额
     */
    @GET("account/checkBalance")
    fun checkBalance(@Query("userName") userName: String):Observable<BaseEntity<Double>>

    /**
     * 登陆panda体育
     */
    @GET("user/loginPanda")
    fun loginPanda(
        @Query("userName") userName: String,
        @Query("terminal") terminal: String
    ): Observable<BaseEntity<DomainBean>>

    /**
     * 平台转账
     * @param transferType 1 上分(欧宝减钱,panda 加钱) 2 下分 (相反)
     */
    @GET("account/transferPandaCredit")
    fun platformTransfer(
        @Query("userName") userName: String,
        @Query("transferType") transferType:String,
        @Query("amount") amount:String
    ):Observable<BaseEntity<String>>

}