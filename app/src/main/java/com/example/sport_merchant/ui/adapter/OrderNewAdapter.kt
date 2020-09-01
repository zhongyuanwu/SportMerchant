package com.example.sport_merchant.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.sport_merchant.R
import com.example.sport_merchant.http.bean.OrderRecord
import com.example.sport_merchant.widget.OrderNewDetailItem

/**
 * created by jump on 2019/12/31.
 * describe: 我的投注记录 未结算 结算（根据recordStatus判断 是否折叠串关 显示订单号等） 通用适配器
 */
@Suppress("DEPRECATION")
class OrderNewAdapter(layoutResId: Int) :
    BaseQuickAdapter<OrderRecord.RecordsBean, BaseViewHolder>(layoutResId) {
    //R.layout.item_order_new_record
    @SuppressLint("SetTextI18n")
    override fun convert(helper: BaseViewHolder, item: OrderRecord.RecordsBean) {
        item.run {
            //投注方式 单关 或 串关
            val tvBetPlayAway = helper.getView<TextView>(R.id.tv_bet_play_away)
            tvBetPlayAway.text = seriesValue
            //投注状态
            val tvBetStatus = helper.getView<TextView>(R.id.tv_bet_status)
            //输赢 或者 最高可赢
            val tvGetMoney = helper.getView<TextView>(R.id.tv_get_money_new)
            when (orderStatus.toInt()) {
                3 -> {// 3:待确认
                    tvBetStatus.setBackgroundResource(R.drawable.ic_order_sure)
                    tvBetStatus.text = "注单确认中"
                    helper.getView<TextView>(R.id.tv_get_money_title).text = "最高可赢(RMB)"
                    tvGetMoney.text = maxWinAmount
                }
                2, 4 -> {// 2:取消交易 4:已拒绝
                    tvBetStatus.setBackgroundResource(R.drawable.ic_order_fail)
                    tvBetStatus.text = "投注失败"
                    helper.getView<TextView>(R.id.tv_get_money_title).text = "最高可赢(RMB)"
                    tvGetMoney.text = maxWinAmount
                }
                else -> {//0 1:已处理
                    tvBetStatus.setBackgroundResource(R.drawable.ic_order_succss)
                    tvBetStatus.text = "投注成功"
                    if (orderStatus == "0") {//未结算
                        helper.getView<TextView>(R.id.tv_get_money_title).text = "最高可赢(RMB)"
                        tvGetMoney.text = maxWinAmount
                    } else {//已结算
                        helper.getView<TextView>(R.id.tv_get_money_title).text = "输/赢(RMB)"
                        tvGetMoney.text = profitAmount ?: ""
                        if (profitAmount != null) {
                            if (profitAmount.isNotEmpty()) {
                                if (profitAmount.contains("-") || profitAmount == "0.00") {
                                    tvGetMoney.setTextColor(mContext.resources.getColor(R.color.order_new_text))
                                } else {
                                    tvGetMoney.setTextColor(mContext.resources.getColor(R.color.order_new_red))
                                }
                            }
                        }
                    }

                }
            }
            //投注金额 马来盘和印尼盘有负水
            helper.getView<TextView>(R.id.tv_bet_money_new).text = orderAmountTotal
            val tvOrderAddition = helper.getView<TextView>(R.id.tv_order_addition)
            if (addition == 0.0) {
                tvOrderAddition.visibility = View.INVISIBLE
            } else {
                //印尼盘+ 马来盘 -
                val value = addition
                if (addition > 0) {
                    tvOrderAddition.text = "[+$value]"
                } else {
                    tvOrderAddition.text = "[$value]"
                }
                tvOrderAddition.visibility = View.VISIBLE
            }
            //添加比赛详情 串关有多个
            val llExpand = helper.getView<LinearLayout>(R.id.ll_expand)
            val llCollapse = helper.getView<LinearLayout>(R.id.ll_collapse)
            val container = helper.getView<LinearLayout>(R.id.ll_new_container).apply {
                val update = childCount == orderVOS.size
                if (!update) {
                    removeAllViews()
                }
                var size = orderVOS.size
                if (size > 2 && !isExpanded) {
                    llExpand.visibility = View.VISIBLE
                    llCollapse.visibility = View.GONE
                } else if (size > 2) {
                    llCollapse.visibility = View.VISIBLE
                    llExpand.visibility = View.GONE
                } else {
                    llExpand.visibility = View.GONE
                    llCollapse.visibility = View.GONE
                }

                for (i in orderVOS.indices) {
                    var detailItem: OrderNewDetailItem
                    if (update) {
                        detailItem = getChildAt(i) as OrderNewDetailItem
                    } else {
                        detailItem = OrderNewDetailItem(context)
                        addView(detailItem)
                    }
                    if (i != 0 && size > 2) {
                        detailItem.visibility = if (isExpanded) View.VISIBLE else View.GONE
                    } else {
                        detailItem.visibility = View.VISIBLE
                    }
                    detailItem.bindData(orderVOS[i], marketType, seriesType == "1")
                }
            }
            llExpand.setOnClickListener {
                llExpand.visibility = View.GONE
                llCollapse.visibility = View.VISIBLE
                for (i in 0 until container.childCount) {
                    if (i != 0) {
                        container.getChildAt(i).visibility =
                            if (isExpanded) View.GONE else View.VISIBLE
                    }
                }
                isExpanded = !isExpanded
            }
            llCollapse.setOnClickListener {
                llExpand.visibility = View.VISIBLE
                llCollapse.visibility = View.GONE
                for (i in 0 until container.childCount) {
                    if (i != 0) {
                        container.getChildAt(i).visibility =
                            if (isExpanded) View.GONE else View.VISIBLE
                    }
                }
                isExpanded = !isExpanded
            }
        }

    }
}