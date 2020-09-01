package com.example.sport_merchant.ui.contract

import com.example.base.IPresenter
import com.example.base.IView
import com.example.sport_merchant.http.bean.OrderRecord

/**
 * created by jump on 2020/8/23
 * describe:
 */
interface OrderRecordContract {
    interface View : IView {
        fun onFinishRefresh(
            orderRecords: List<OrderRecord.RecordsBean>,
            noMoreData: Boolean
        )

        fun onLoadMore(
            orderRecords: List<OrderRecord.RecordsBean>,
            noMoreData: Boolean
        )
    }

    interface Presenter : IPresenter<View> {
        /**
         * 根据订单状态查询订单
         * @param settleStatus 结算状态
         * @param  timeType 查询的时间段
         */
        fun getOrdersByStatus(
            settleStatus: Int,
            timeType: Int,
            isRefresh: Boolean,
            isShowDialog: Boolean
        )
    }
}