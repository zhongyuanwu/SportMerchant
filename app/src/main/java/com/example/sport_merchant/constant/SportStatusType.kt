package com.example.sport_merchant.constant

/**
 * created by jump on 2020/8/23
 * describe:
 */
enum class SportStatusType(val id: Int, val value: Int, val des: String) {
    Platform(0,100,"平台"),
    SettleStatus(1,101,"结算状态"),
    Panda(2,102,"熊猫体育"),
    YaBo(3,103,"亚博体育"),
    UnSettlement(5,0,"未结算"),
    Settlement(4,1,"已结算");
}