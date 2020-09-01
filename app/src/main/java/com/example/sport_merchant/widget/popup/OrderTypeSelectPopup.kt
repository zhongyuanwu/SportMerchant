package com.example.sport_merchant.widget.popup

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.sport_merchant.R
import com.example.sport_merchant.constant.SportStatusType
import razerdp.basepopup.BasePopupWindow

/**
 * created by jump on 2020/8/23
 * describe: type 0 平台 1 结算状态
 */
class OrderTypeSelectPopup(context: Context?, type: SportStatusType) : BasePopupWindow(context) {

    override fun onCreateContentView(): View = createPopupById(R.layout.popup_order_type_select)

    init {
        setBackgroundColor(0)
        val rbOne = findViewById<RadioButton>(R.id.rb_one)
        val rbTwo = findViewById<RadioButton>(R.id.rb_two)
        val rgRoot = findViewById<RadioGroup>(R.id.rg_root)
        if (type == SportStatusType.Platform) {
            rbOne.text = "熊猫体育"
            rbOne.isClickable = false
            rbTwo.text = "亚博体育"
            rbTwo.isClickable = false
        } else if (type == SportStatusType.SettleStatus) {
            rbOne.text = "未结算"
            rbTwo.text = "已结算"
        }
        rgRoot.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_one -> {
                    if (type == SportStatusType.Platform) {
                        onSportStatusListener?.onStatusBack(SportStatusType.Panda)
                    } else {
                        onSportStatusListener?.onStatusBack(SportStatusType.UnSettlement)
                    }
                }
                R.id.rb_two -> {
                    if (type == SportStatusType.Platform) {
                        onSportStatusListener?.onStatusBack(SportStatusType.YaBo)
                    } else {
                        onSportStatusListener?.onStatusBack(SportStatusType.Settlement)
                    }
                }
            }
            dismiss()
        }
    }

    private var onSportStatusListener: OnSportStatusListener? = null

    fun setOnSportStatusListener(onSportStatusListener: OnSportStatusListener) {
        this.onSportStatusListener = onSportStatusListener
    }

    interface OnSportStatusListener {
        fun onStatusBack(status: SportStatusType)
    }
}