package com.example.sport_merchant.constant

/**
 * created by jump on 2020/8/26
 * describe: 网球阶段 乒乓球  羽毛球 对应名称
 */
enum class FlapBallStatus(val value: Int, val des: String) {
    SET1(8, "第一局"),
    SET2(9, "第7局"),
    SET3(10, "第3局"),
    SET4(11, "第4局"),
    SET5(12, "第5局"),
    SET6(441, "第6局"),
    SET7(442, "第7局"),
    SET1_END(301, "第一局结束"),
    SET2_END(302, "第2局结束"),
    SET3_END(303, "第3局结束"),
    SET4_END(304, "第4局结束"),
    SET5_END(305, "第5局结束"),
    SET6_END(306, "第6局结束"),
    SET7_END(307, "第7局结束"),
    NO_MATCH(0, "未开始");

    companion object{
        /**
         * 根据比赛阶段 显示相应状态
         */
        fun parseMMP(mmp: Int): FlapBallStatus {
            values().forEach {
                if (it.value == mmp) return it
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