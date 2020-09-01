package com.example.sport_merchant.constant

/**
 * created by jump on 2020/8/25
 * describe:篮球阶段 枚举数据
 */
enum class BasketballStatusConstant(val value: Int, val des: String) {
    FIRST_HALF(1, "上半场"),

    SECOND_HALF(2, "下半场"),

    FIRST_QUARTER(13, "第一节"),

    FIRST_PAUSE(301, "第一节休息"),

    SECOND_QUARTER(14, "第二节"),

    SECOND_PAUSE(302, "第二节休息"),

    THIRD_QUARTER(15, "第三节"),

    THIRD_PAUSE(303, "第三节休息"),

    FOURTH_QUARTER(16, "第四节"),

    NO_MATCH(0,"未开始");

    companion object{
        /**
         * 根据比赛阶段 显示相应状态
         */
        fun parseMMP(mmp:Int):BasketballStatusConstant{
            values().forEach {
                if(it.value==mmp) return it
            }
            return NO_MATCH
        }
        fun hasStatus(mmp: Int):Boolean{
            values().forEach {
                if(it.value==mmp) return true
            }
            return false
        }
    }

}