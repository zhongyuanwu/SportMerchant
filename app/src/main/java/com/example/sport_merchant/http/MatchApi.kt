package com.example.sport_merchant.http

import com.example.base.BaseEntity
import com.example.sport_merchant.http.bean.MatchBean
import com.example.sport_merchant.http.bean.OrderRecord
import com.example.sport_merchant.http.request.MatchRequest
import com.example.sport_merchant.http.request.RecordRequest
import com.panda.flycotablayout.TabEntity
import io.reactivex.Observable
import retrofit2.http.*

/**
 * created by jump on 2020/8/23
 * describe:
 */
interface MatchApi {
    /**
     * 体育菜单
     */
    @GET("pub/v1/m/menu/init")
    fun getMenus(): Observable<BaseEntity<List<TabEntity>>>

    /**
     * 获取赛事列表
     */
    @POST("v2/m/GetMatch")
    fun getMatch(@Body param: MatchRequest): Observable<BaseEntity<List<MatchBean>>>

    /**
     * 获取注单列表
     */
    @POST("http://api.sportxxx278gwf4.com/yewu13/v1/betOrder/getOrderList")
    fun getOrder(@Body param: RecordRequest):Observable<BaseEntity<OrderRecord>>
}