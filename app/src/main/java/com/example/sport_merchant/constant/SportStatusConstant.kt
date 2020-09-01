package com.example.sport_merchant.constant

/**
 * created by jump on 2020/8/25
 * describe:比赛阶段 枚举数据 （所有种类比赛的通用）
 */
enum class SportStatusConstant(val value: Int, val des: String) {
    PAUSED(31, "中场休息"),

    NOT_STARTED(0, "未开赛"),

    AWAITING_OT(32, "即将加时"),

    OVERTIME(40, "加时赛"),

    INTERRUPTED(80, "比赛中断"),

    ABANDONED(90, "比赛放弃"),

    ENDED(100, "全场结束"),

    ENDED1(999, "全场结束"),

    AFTER_OT(110, "加时赛结束"),

    DELAYED(61, "比赛推迟");

   companion object{
       /**
        * 根据比赛阶段 显示相应状态
        */
       fun parseMMP(mmp: Int): SportStatusConstant {
           values().forEach {
               if (it.value == mmp) return it
           }
           return NOT_STARTED
       }
       fun hasStatus(mmp: Int):Boolean{
           values().forEach {
               if(it.value==mmp) return true
           }
           return false
       }
   }

}