package com.example.sport_merchant.constant

/**
 * created by jump on 2020/8/25
 * describe: 足球阶段 枚举数据
 */
enum class FootBallStatusConstant(val value: Int, val des: String) {
    FIRST_HALF(6, "上半场"),

    SECOND_HALF(7, "下半场"),

    EXTRA_TIME(33, "加时休息"),

    O_FIRST_HALF(41, "加时上半场"),

    O_SECOND_HALF(42, "加时下半场"),

    WAIT_PENALTY(34, "即将点球"),

    PEN(50, "点球大战"),

    END_PENALTY_TIME(120, "点球大战结束"),
    NO_MATCH(0,"未开始");

    companion object{
        /**
         * 根据比赛阶段 显示相应状态
         */
        fun parseMMP(mmp:Int):FootBallStatusConstant{
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