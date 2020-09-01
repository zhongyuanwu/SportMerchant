package com.example.base.widget

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.fragment.app.Fragment
import com.example.base.R
import razerdp.basepopup.BasePopupWindow

/**
 * created by jump on 2020/8/22
 * describe:
 */
class KProgressHUDPop : BasePopupWindow {
    private var mContext: Context? = null

    constructor(context: Context?) : super(context) {
        mContext = context
    }

    constructor(fragment: Fragment?) : super(fragment)

    constructor(dialog: Dialog?) : super(dialog)

    override fun onCreateContentView(): View = createPopupById(R.layout.kprogresshud_hud)

    init {
        setBackgroundColor(Color.TRANSPARENT)
    }
}