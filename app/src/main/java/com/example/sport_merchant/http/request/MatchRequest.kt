package com.example.sport_merchant.http.request

/**
 * created by jump on 2020/8/28
 * describe:
 */
class MatchRequest {
    var type : String? = null//一级菜单筛选类型 1滚球 2 即将开赛 3今日赛事 4早盘11串
    var euid : String? = null//关赛事 id 多个用逗号分隔
    var md: String?=null
    var cps = 0//最大条数
}