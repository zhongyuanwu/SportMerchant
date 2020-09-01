package com.example.sport_merchant.http.bean

/**
 * created by jump on 2020/8/22
 * describe:
 */
data class MenusTabBean(
    val containLive: Boolean,
    val count: Int,
    val dayOfWeek: String,
    val field1: String,
    val field2: String,
    val grade: Int,
    val imgUrl: String,
    val menuId: Int,
    val menuName: String,
    val menuType: Int,
    val picAddress: String,
    val subList: List<MenusTabBean>,
    val topMenuList: List<MenusTabBean>
)