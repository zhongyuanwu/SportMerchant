package com.example.sport_merchant.ui.contract

import com.example.base.IPresenter
import com.example.base.IView
import com.example.sport_merchant.http.bean.MatchBean
import com.panda.flycotablayout.TabEntity

/**
 * created by jump on 2020/8/22
 * describe:
 */
interface HomeContract {
    interface View : IView {
        fun updateBalance(balance:Double)
        fun setMenusListData(tabEntity: List<TabEntity>,refreshCount:Boolean)
        fun showMatchList(matchBean: List<MatchBean>)
        fun transferSuccess(menusId:String)
    }

    interface Presenter : IPresenter<View> {
        fun checkBalance(userName: String)
        fun loginPanda(userName: String)
        fun getMatchList(parentId: String, childId: String)
        fun platformTransferToH5(userName: String,amount:String,menusId:String)
    }
}