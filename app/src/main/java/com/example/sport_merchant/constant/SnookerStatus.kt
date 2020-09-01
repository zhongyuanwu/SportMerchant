package com.example.sport_merchant.constant

/**
 * created by jump on 2020/8/26
 * describe:斯诺克阶段 枚举数据
 *
 */
enum class SnookerStatus(val value:Int,val des:String) {
    IN_PROGRESS(21,"进行中"),
    PAUSE(30,"暂停"),
    SESSION_BREAK(445,"局间休息"),
    NO_MATCH(0,"未开始");

    companion object{
        /**
         * 根据比赛阶段 显示相应状态
         */
        fun parseMMP(mmp:Int):SnookerStatus{
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