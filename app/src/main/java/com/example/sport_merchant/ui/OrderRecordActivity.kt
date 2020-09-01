package com.example.sport_merchant.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.BaseMvpActivity
import com.example.sport_merchant.R
import com.example.sport_merchant.constant.SportStatusType
import com.example.sport_merchant.constant.TimeType
import com.example.sport_merchant.http.bean.OrderRecord
import com.example.sport_merchant.ui.adapter.OrderNewAdapter
import com.example.sport_merchant.ui.contract.OrderRecordContract
import com.example.sport_merchant.ui.presenter.OrderRecordPresenter
import com.example.sport_merchant.widget.EmptyView
import com.example.sport_merchant.widget.popup.OrderTimeSelectPopup
import com.example.sport_merchant.widget.popup.OrderTypeSelectPopup
import kotlinx.android.synthetic.main.activity_order_record.*

/**
 * created by jump on 2020/8/23
 * describe:
 */
class OrderRecordActivity :
    BaseMvpActivity<OrderRecordContract.View, OrderRecordContract.Presenter>(),
    OrderRecordContract.View {

    private val sportTypePopup: OrderTypeSelectPopup by lazy {
        OrderTypeSelectPopup(this, SportStatusType.Platform)
    }
    private val orderStatusPopup: OrderTypeSelectPopup by lazy {
        OrderTypeSelectPopup(this, SportStatusType.SettleStatus)
    }
    private val orderTimePopup: OrderTimeSelectPopup by lazy {
        OrderTimeSelectPopup(this)
    }

    private val mAdapter: OrderNewAdapter by lazy {
        OrderNewAdapter(R.layout.item_order_new_record)
    }

    private var currentTimeType: TimeType = TimeType.TODAY
    private var currentSportStatusType: SportStatusType = SportStatusType.UnSettlement

    override fun createPresenter(): OrderRecordContract.Presenter = OrderRecordPresenter()

    override fun attachLayoutRes(): Int = R.layout.activity_order_record

    override fun initView() {
        super.initView()
        iv_back.setOnClickListener { finish() }
        sportTypePopup.setOnSportStatusListener(object :
            OrderTypeSelectPopup.OnSportStatusListener {
            override fun onStatusBack(status: SportStatusType) {
                sport_type.text = status.des
            }

        })
        orderStatusPopup.setOnSportStatusListener(object :
            OrderTypeSelectPopup.OnSportStatusListener {
            override fun onStatusBack(status: SportStatusType) {
                currentSportStatusType = status
                order_status.text = status.des
                mPresenter?.getOrdersByStatus(
                    currentSportStatusType.value, currentTimeType.value, true,
                    isShowDialog = true
                )
            }
        })
        orderTimePopup.setOnTimeSelectListener(object : OrderTimeSelectPopup.OnTimeSelectListener {
            override fun onTimeBack(time: TimeType) {
                currentTimeType = time
                order_time.text = time.des
                mPresenter?.getOrdersByStatus(
                    currentSportStatusType.value, currentTimeType.value, true,
                    isShowDialog = true
                )
            }
        })
        sport_type.setOnClickListener {
            if (sportTypePopup.isShowing) sportTypePopup.dismiss() else sportTypePopup.showPopupWindow(
                record_line
            )
        }
        order_status.setOnClickListener {
            if (orderStatusPopup.isShowing) orderStatusPopup.dismiss() else orderStatusPopup.showPopupWindow(
                record_line
            )
        }

        order_time.setOnClickListener {
            if (orderTimePopup.isShowing) orderTimePopup.dismiss() else orderTimePopup.showPopupWindow(
                record_line
            )
        }

        refresh_new.run {
            setOnRefreshListener {
                mPresenter?.getOrdersByStatus(
                    currentSportStatusType.value, currentTimeType.value, true,
                    isShowDialog = false
                )
            }
            setOnLoadMoreListener {
                mPresenter?.getOrdersByStatus(
                    currentSportStatusType.value, currentTimeType.value, false,
                    isShowDialog = false
                )
            }
        }
        ry_record.run {
            layoutManager = LinearLayoutManager(this@OrderRecordActivity)
            adapter = mAdapter
        }

        mAdapter.emptyView = EmptyView(this).apply { show("暂无投注记录") }
    }

    override fun initData() {
        super.initData()
        mPresenter?.getOrdersByStatus(
            currentSportStatusType.value, currentTimeType.value, true,
            isShowDialog = true
        )
    }

    override fun onFinishRefresh(orderRecords: List<OrderRecord.RecordsBean>, noMoreData: Boolean) {
        mAdapter.setNewData(orderRecords)
        refresh_new.setNoMoreData(noMoreData)
        refresh_new.finishRefresh(true)
    }

    override fun onLoadMore(orderRecords: List<OrderRecord.RecordsBean>, noMoreData: Boolean) {
        if (!noMoreData) mAdapter.addData(orderRecords)
        refresh_new.setNoMoreData(noMoreData)
        refresh_new.finishLoadMore(true)
    }
}