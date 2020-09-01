package com.example.sport_merchant.ui.presenter

import com.example.base.BasePresenter
import com.example.sport_merchant.ext.commHandler
import com.example.sport_merchant.http.UserRetrofit
import com.example.sport_merchant.http.request.LoginRequest
import com.example.sport_merchant.ui.contract.LoginContract

/**
 * created by jump on 2020/8/21
 * describe:
 */
class LoginPresenter : BasePresenter<LoginContract.View>(), LoginContract.Presenter {
    override fun login(userName: String, passWord: String) {
        val loginRequest = LoginRequest(userName, passWord,"h5-1")
        UserRetrofit.service.login(loginRequest).commHandler(
            mView, true, {
                mView?.loginSuccess(it)
            }, { msg, _ ->
                mView?.loginFail(msg)
            }
        )
    }

}