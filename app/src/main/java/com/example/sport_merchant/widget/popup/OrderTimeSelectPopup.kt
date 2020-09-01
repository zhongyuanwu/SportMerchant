package com.example.sport_merchant.widget.popup

import android.content.Context
import android.view.View
import android.widget.RadioGroup
import com.example.sport_merchant.R
import com.example.sport_merchant.constant.TimeType
import razerdp.basepopup.BasePopupWindow

/**
 * created by jump on 2020/8/23
 * describe:
 */
class OrderTimeSelectPopup(context: Context?) : BasePopupWindow(context) {
    override fun onCreateContentView(): View = createPopupById(R.layout.popup_order_time_select)

    init {
        setBackgroundColor(0)
        val rgTime = findViewById<RadioGroup>(R.id.rg_time)
        rgTime.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_today -> {
                    onTimeSelectListener?.onTimeBack(TimeType.TODAY)
                }
                R.id.rb_seven_day -> {
                    onTimeSelectListener?.onTimeBack(TimeType.SEVEN_DAY)
                }
                R.id.rb_third_day -> {
                    onTimeSelectListener?.onTimeBack(TimeType.THIRD_DAY)
                }
            }

            dismiss()
        }
    }
    //0 今天
    private var onTimeSelectListener: OnTimeSelectListener? = null
    fun setOnTimeSelectListener(onTimeSelectListener: OnTimeSelectListener) {
        this.onTimeSelectListener = onTimeSelectListener
    }

    interface OnTimeSelectListener {
        fun onTimeBack(time: TimeType)
    }
}