package com.example.sport_merchant.ui.contract

import com.example.base.IPresenter
import com.example.base.IView
import com.example.sport_merchant.http.bean.UserInfo

/**
 * created by jump on 2020/8/21
 * describe:
 */
interface LoginContract {
    interface View : IView {
        fun loginSuccess(userInfo: UserInfo)
        fun loginFail(msg:String)
    }

    interface Presenter : IPresenter<View> {
        fun login(userName: String, passWord: String)
    }
}