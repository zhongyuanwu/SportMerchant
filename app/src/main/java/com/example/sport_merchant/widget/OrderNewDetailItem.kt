package com.example.sport_merchant.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.base.utils.TimeDateUtils
import com.example.sport_merchant.R
import com.example.sport_merchant.http.bean.OrderRecord
import kotlinx.android.synthetic.main.item_order_new_detail.view.*

/**
 * created by jump on 2019/12/31.
 * describe:
 */
@Suppress("DEPRECATION")
class OrderNewDetailItem @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_order_new_detail, this)
    }

    /**
     * @param isSingle 是否单关
     */
    @SuppressLint("SetTextI18n")
    fun bindData(orderBean: OrderRecord.RecordsBean.OrderVOS, marketType: String, isSingle: Boolean) {
        orderBean.run {
            line3.visibility = if (isSingle) View.VISIBLE else View.INVISIBLE
            tv_league_name.text = matchName
            tv_game_time.text = "比赛时间：${TimeDateUtils.getFormedDateByString(beginTime, "yyyy-MM-dd  HH:mm")}"
            tv_game_team_info.text = matchInfo
            tv_game_type.text = playName
            tv_bet_content.text = marketValue
//            if (score.isNotEmpty() && outCome.isNotEmpty() && outCome != "0") {
//                if (score.contains(":")) {
//                    score = score.replace(":", "-")
//                }
//                tv_bet_score.text = "全场 $score"
//            } else {
//                tv_bet_score.text = ""
//            }
            tv_bet_odds.text = oddFinally
            if (marketType == "MY" || marketType == "ID") {
                val number = oddFinally.toDouble()
                if (number < 0) {
                    tv_bet_odds.setTextColor(ContextCompat.getColor(context, R.color.bet_negative_water))
                    tv_bet_fuhao.setTextColor(ContextCompat.getColor(context, R.color.bet_negative_water))
                } else {
                    tv_bet_odds.setTextColor(ContextCompat.getColor(context, R.color.order_new_odds))
                    tv_bet_fuhao.setTextColor(ContextCompat.getColor(context, R.color.order_new_odds))
                }
            } else {
                tv_bet_odds.setTextColor(ContextCompat.getColor(context, R.color.order_new_odds))
                tv_bet_fuhao.setTextColor(ContextCompat.getColor(context, R.color.order_new_odds))
            }
            tv_bet_result.visibility = View.VISIBLE
            when (betResult) {
                2 -> {
                    tv_bet_result.text = "走水"
                    tv_bet_result.setTextColor(ContextCompat.getColor(context, R.color.order_new_text))
                }
                3 -> {
                    tv_bet_result.text = "输"
                    tv_bet_result.setTextColor(ContextCompat.getColor(context, R.color.order_new_text))
                }
                4 -> {
                    tv_bet_result.text = "赢"
                    tv_bet_result.setTextColor(ContextCompat.getColor(context, R.color.order_new_red))
                }
                5 -> {
                    tv_bet_result.text = "赢半"
                    tv_bet_result.setTextColor(ContextCompat.getColor(context, R.color.order_new_red))
                }
                6 -> {
                    tv_bet_result.text = "输半"
                    tv_bet_result.setTextColor(ContextCompat.getColor(context, R.color.order_new_text))
                }
                7 -> {
                    tv_bet_result.text = "赛事取消"
                    tv_bet_result.setTextColor(ContextCompat.getColor(context, R.color.order_new_text_un))
                }
                8 -> {
                    tv_bet_result.text = "赛事延期"
                    tv_bet_result.setTextColor(ContextCompat.getColor(context, R.color.order_new_text_un))
                }
                else -> {
                    tv_bet_result.visibility = View.INVISIBLE
                }
            }
        }

    }
}