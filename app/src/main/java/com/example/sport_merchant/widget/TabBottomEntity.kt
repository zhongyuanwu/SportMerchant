package com.example.sport_merchant.widget

import com.panda.flycotablayout.listener.CustomTabEntity


/**
 * created by jump on 2020/8/22
 * describe:
 */
class TabBottomEntity(var title: String, var selectedIcon: Int, var unSelectedIcon: Int) :
    CustomTabEntity {

    override fun getTabUnselectedIcon(): Int = unSelectedIcon

    override fun getTabSelectedIcon(): Int = selectedIcon

    override fun getTabTitle(): String = title
}