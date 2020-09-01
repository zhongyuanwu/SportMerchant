package com.example.sport_merchant.ui.contract

import com.example.base.IPresenter
import com.example.base.IView
import com.example.sport_merchant.http.bean.UserInfo

/**
 * created by jump on 2020/8/22
 * describe:
 */
interface MainContract {
    interface View : IView {
        fun updateUserInfo(userInfo: UserInfo)
    }

    interface Presenter : IPresenter<View> {
        fun getUserInfo(token: String)
    }
}