package com.example.sport_merchant.ui.presenter

import android.util.Log
import com.example.base.BasePresenter
import com.example.sport_merchant.ext.commHandler
import com.example.sport_merchant.http.MatchRetrofit
import com.example.sport_merchant.http.request.RecordRequest
import com.example.sport_merchant.ui.contract.OrderRecordContract

/**
 * created by jump on 2020/8/23
 * describe:
 */
class OrderRecordPresenter : BasePresenter<OrderRecordContract.View>(),
    OrderRecordContract.Presenter {
    private var page = 1
    override fun getOrdersByStatus(
        settleStatus: Int,
        timeType: Int,
        isRefresh: Boolean,
        isShowDialog: Boolean
    ) {
        page = if (isRefresh) 1 else ++page
        val recordRequest = RecordRequest(page, 20, timeType, settleStatus)
        MatchRetrofit.service.getOrder(recordRequest).commHandler(mView, isShowDialog, {
            if (isRefresh) {
                mView?.onFinishRefresh(it.records, it.current >= it.pages)
            } else {
                mView?.onLoadMore(it.records, it.current >= it.pages)
            }
        })
    }
}