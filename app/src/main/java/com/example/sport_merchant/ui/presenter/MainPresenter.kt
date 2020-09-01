package com.example.sport_merchant.ui.presenter

import com.example.base.BasePresenter
import com.example.sport_merchant.ext.commHandler
import com.example.sport_merchant.http.UserRetrofit
import com.example.sport_merchant.ui.contract.MainContract

/**
 * created by jump on 2020/8/22
 * describe:
 */
class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {
    override fun getUserInfo(token: String) {
        UserRetrofit.service.getUserInfo(token).commHandler(mView, false, {
            mView?.updateUserInfo(it)

        })
    }

}