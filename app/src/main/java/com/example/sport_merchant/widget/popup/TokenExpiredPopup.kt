package com.example.sport_merchant.widget.popup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.View
import android.widget.Button
import com.example.sport_merchant.R
import com.example.sport_merchant.constant.UserManager
import com.example.sport_merchant.ui.LoginActivity
import razerdp.basepopup.BasePopupWindow

/**
 * created by jump on 2020/8/26
 * describe:
 */
class TokenExpiredPopup(activity: Activity) : BasePopupWindow(activity) {
    override fun onCreateContentView(): View = createPopupById(R.layout.popup_token_expired)

    init {
        popupGravity = Gravity.CENTER
//        setBackgroundColor(0)
        val btnSure = findViewById<Button>(R.id.btn_sure)
        btnSure.setOnClickListener {
            dismiss()
            UserManager.get().outLogin(context)
        }
    }
}