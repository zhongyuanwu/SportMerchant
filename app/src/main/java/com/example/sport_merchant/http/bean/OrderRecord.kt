package com.example.sport_merchant.http.bean

/**
 * created by jump on 2020/8/23
 * describe:
 */
data class OrderRecord(
    val betTotalAmount: String,
    val current: String,
    val effectiveFlow: String,//有效流水
    val pages: String,//总页数
    val profit: String,//总盈利
    val size: String,//每页显示大小
    val total: String,//总数条数
    val searchCount: Boolean,
    val records: List<RecordsBean>
) {
    data class RecordsBean(
        var isExpanded: Boolean = false,
        val addition: Double,//印尼、马来盘附加金额，如果大于0需要商户在该字段前加“+”号(针对印尼盘和马来盘做的特殊处理 实际付款金额-投注金额)
        val betTime: Long,//投注时间
        val marketType: String,//盘口类型
        val maxWinAmount: String,//最高可赢
        val orderAmountTotal: String,//投注总金额
        val orderNo: String,//订单号
        val orderStatus: String,//订单状态(0:未结算,1:已结算,2:注单取消,3:确认中,4:投注失败)
        val seriesSum: Int,//串关注数
        val seriesType: String,//串关类型(单关、串关)
        val seriesValue: String,//串关值
        val singerOrderAmount: Double,//注单金额
        val profitAmount:String?=null,
        val outcome:String?=null,////输赢结算状态2是走水，3-输，4-赢，5-半赢，6-半输，7赛事取消，8赛事延期
        val backAmount:String?=null,
        val orderVOS: List<OrderVOS>
    ) {
        data class OrderVOS(
            val playId:Int,
            val beginTime: Long,//开始时间
            val marketValue: String,//盘口值
            val matchInfo: String,//对阵信息
            val matchName: String,//投注联赛
            val matchType: String,//赛事类型：1 ：早盘赛事 ，2： 滚球盘赛事，3： 冠军盘赛事
            val oddFinally: String,//最终赔率
            val oddsValue: String,//投注赔率
            val playName: String,//玩法名称
            val playOptionsId:String,//投注类型ID(对应上游的投注项ID)
            val scoreBenchmark: String,//基准比分
            val settleScore: String,//结算比分
            val sportName: String,//体种类名称
            val betResult:Int,//投注项结算结果0-无结果 2-走水 3-输 4-赢 5-赢一半 6-输一半 7-赛事取消 8-赛事延期 11-比赛延迟 12-比赛中断 13-未知 15-比赛放弃 16-异常盘口
            val betStatus:Int//注单状态(0未结算 1已结算 2结算异常 3注单取消)
        )
    }
}