package com.example.sport_merchant.ui.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.base.utils.GlideUtils
import com.example.base.utils.TimeDateUtils
import com.example.sport_merchant.R
import com.example.sport_merchant.constant.*
import com.example.sport_merchant.constant.FootBallStatusConstant.END_PENALTY_TIME
import com.example.sport_merchant.constant.FootBallStatusConstant.EXTRA_TIME
import com.example.sport_merchant.constant.FootBallStatusConstant.O_FIRST_HALF
import com.example.sport_merchant.constant.FootBallStatusConstant.O_SECOND_HALF
import com.example.sport_merchant.constant.FootBallStatusConstant.PEN
import com.example.sport_merchant.constant.FootBallStatusConstant.WAIT_PENALTY
import com.example.sport_merchant.constant.SportStatusConstant.ABANDONED
import com.example.sport_merchant.constant.SportStatusConstant.AFTER_OT
import com.example.sport_merchant.constant.SportStatusConstant.AWAITING_OT
import com.example.sport_merchant.constant.SportStatusConstant.INTERRUPTED
import com.example.sport_merchant.http.bean.MatchBean
import java.util.*

/**
 * created by jump on 2020/8/23
 * describe:赛事列表 适配器
 */
class MatchListAdapter(layoutResId: Int) :
    BaseQuickAdapter<MatchBean, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder, item: MatchBean) {
        val tvGameTitle = helper.getView<TextView>(R.id.tv_game_title)
        tvGameTitle.text = item.tn
        val tvGameScore = helper.getView<TextView>(R.id.tv_game_score)
        if (item.mmp == "0") {//未开始
            //显示开赛时间
            tvGameScore.text = TimeDateUtils.getFormedDateByString(item.mgt.toLong(), "MM-dd HH:mm")
        } else {
            tvGameScore.text = if (item.csid == "1") {//足球
                getFootballScore(item.mmp.toInt(), item.msc)
            } else {//其他显示全场比分
                formatStringMscToMap(item.msc)["S1"]
            }
        }


        val tvHomeIcon = helper.getView<TextView>(R.id.tv_home_icon)
        if (item.mhlu.isNotEmpty()) {
            setLogo(Constant.IMAGE_HOST_URL + item.mhlu, tvHomeIcon)
        } else {
            tvHomeIcon.background =
                ContextCompat.getDrawable(mContext, R.drawable.shape_circle_logo)
            tvHomeIcon.text = item.frmhn
        }
        val tvHomeName = helper.getView<TextView>(R.id.tv_home_name)
        tvHomeName.text = item.mhn

        val tvAwayIcon = helper.getView<TextView>(R.id.tv_away_icon)
        if (item.malu.isNotEmpty()) {
            setLogo(Constant.IMAGE_HOST_URL + item.malu, tvAwayIcon)
        } else {
            tvHomeIcon.background =
                ContextCompat.getDrawable(mContext, R.drawable.shape_circle_logo)
            tvHomeIcon.text = item.frman
        }
        val tvAwayName = helper.getView<TextView>(R.id.tv_away_name)
        tvAwayName.text = item.man

        //比赛阶段
        val tvGameStage = helper.getView<TextView>(R.id.tv_game_stage)
        tvGameStage.text =
            if (item.mmp != "0" && item.mst != "0")
                getStage(item) + "  " + TimeDateUtils.getMinuteSeconds(item.mst.toInt())
            else "未开始"

        val ivAnim = helper.getView<ImageView>(R.id.iv_anim)
        ivAnim.visibility = if (item.mvs == -1) View.GONE else View.VISIBLE
        val ivLive = helper.getView<ImageView>(R.id.iv_live)
        ivLive.visibility = when (item.mms) {
            -1 -> {
                View.GONE
            }
            0 -> {
                ivLive.setImageResource(R.drawable.ic_live_n)
                View.VISIBLE
            }
            else -> {
                ivLive.setImageResource(R.drawable.ic_live_s)
                View.VISIBLE
            }
        }
    }

    private fun setLogo(url: String, view: TextView) {
        GlideUtils.with(mContext).loadImageDrawable(url, object : CustomTarget<Drawable>() {
            override fun onLoadCleared(placeholder: Drawable?) {

            }

            override fun onResourceReady(
                resource: Drawable,
                transition: Transition<in Drawable>?
            ) {
                view.background = resource
                view.text = ""
            }
        })
    }

    private fun getStage(item: MatchBean): String {
        val mmpStatus = item.mmp.toInt()
        if (SportStatusConstant.hasStatus(mmpStatus)) {
            return SportStatusConstant.parseMMP(mmpStatus).des
        }
        return when (item.csid) {
            "1" -> {//足球
                FootBallStatusConstant.parseMMP(mmpStatus).des
            }
            "2" -> {//篮球
                BasketballStatusConstant.parseMMP(mmpStatus).des
            }
            "7" -> {//斯诺克
                SnookerStatus.parseMMP(mmpStatus).des
            }
            "5", "8", "10" -> {//根据比赛阶段获取乒乓球 网球 羽毛球对应阶段名称
                FlapBallStatus.parseMMP(mmpStatus).des
            }
            else -> {
                "未开始"
            }
        }
    }

    private fun getFootballScore(stage: Int, msc: List<String>): String {
        val mscMap = formatStringMscToMap(msc)
        return when (stage) {
            EXTRA_TIME.value, O_FIRST_HALF.value, O_SECOND_HALF.value, AFTER_OT.value -> {
                mscMap["S7"].toString()
            }
            WAIT_PENALTY.value, AWAITING_OT.value -> {
                "0-0"
            }
            PEN.value, END_PENALTY_TIME.value -> {
                if (mscMap["S7"]?.isEmpty()!!) {
                    mscMap["S10"].toString()
                } else {
                    mscMap["S170"].toString()
                }
            }
            ABANDONED.value -> ""
            INTERRUPTED.value -> {
                when {
                    mscMap.containsKey("S10") -> {
                        mscMap["S10"].toString()
                    }
                    mscMap.containsKey("S7") -> {
                        mscMap["S7"].toString()
                    }
                    else -> {
                        mscMap["S1"].toString()
                    }
                }
            }
            else -> {
                if (mscMap["S1"] == null) {
                    "0-0"
                } else {
                    mscMap["S1"].toString()
                }
            }
        }
    }

    private fun formatStringMscToMap(mscs: List<String>): Map<String, String> {
        val mscMap: MutableMap<String, String> =
            HashMap()
        if (mscs.isNotEmpty()) {
            for (msc in mscs) {
                val strings = msc.split("\\|".toRegex()).toTypedArray()
                if (strings.size >= 2 && strings[0].isNotEmpty() && strings[1].isNotEmpty()
                ) {
                    var value = strings[1]
                    if (strings[1].contains(":")) {
                        value = value.replace(":", "-")
                    }
                    mscMap[strings[0]] = value
                }
            }
        }
        return mscMap
    }
}