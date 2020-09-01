package com.example.sport_merchant.ui.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.example.base.BasePresenter
import com.example.base.http.constant.HttpConstant
import com.example.base.utils.SchedulerUtils
import com.example.sport_merchant.constant.UserManager
import com.example.sport_merchant.event.refreshBalanceEvent
import com.example.sport_merchant.ext.commHandler
import com.example.sport_merchant.http.MatchRetrofit
import com.example.sport_merchant.http.UserRetrofit
import com.example.sport_merchant.http.request.MatchRequest
import com.example.sport_merchant.ui.contract.HomeContract
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus

/**
 * created by jump on 2020/8/22
 * describe:
 */
class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {
    override fun checkBalance(userName: String) {
        UserRetrofit.service.checkBalance(userName).commHandler(mView, false, {
            UserManager.get().setBalance(it)
            mView?.updateBalance(it)
            EventBus.getDefault().post(refreshBalanceEvent())
        })
    }

    @SuppressLint("CheckResult")
    override fun loginPanda(userName: String) {
        mView?.showLoading()
        UserRetrofit.service
            .loginPanda(userName, "h5-1")
            .flatMap {
                HttpConstant.TOKEN = it.data.token
                HttpConstant.DOMAIN = it.data.domain
                MatchRetrofit.service.getMenus()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                val matchRequest = MatchRequest()
                it.data.filter { tab ->
                    //过滤掉除滚球 今日 早盘 串关 以外的数据
                    tab.menuType == 1 || tab.menuType == 3 || tab.menuType == 4 || tab.menuType == 11
                }.let { tabList ->
                    mView?.setMenusListData(tabList, false)
                    //默认 获取一个菜单下的第一个赛事项 如果是滚球 则获取第二个
                    val tabEntity = tabList[0]
                    val subMenuId = if (tabEntity.menuType == 1) {
                        tabEntity.subList[1].menuId.toString()
                    } else {
                        tabEntity.subList[0].menuId.toString()
                    }
                    matchRequest.cps = 10
                    matchRequest.type = tabEntity.menuType.toString()
                    matchRequest.euid = subMenuId
                }
                Observable.just(matchRequest)
            }
            .observeOn(Schedulers.io())
            .flatMap {
                MatchRetrofit.service.getMatch(it)
            }
            .commHandler(mView, true, {
                mView?.showMatchList(it)
            })
    }


    /**
     * 获取赛事列表
     * @param childId 赛事ID 多个用,分割
     * @param parentId 一级菜单筛选类型 1滚球 2 即将开赛 3今日赛事 4早盘11串关
     */
    override fun getMatchList(parentId: String, childId: String) {
        val matchRequest = MatchRequest()
        matchRequest.cps = 10
        matchRequest.type = parentId
        matchRequest.euid = childId
        if (parentId == "11" || parentId == "4") {
            //早盘和串关 获取所有日期赛事
            matchRequest.md = ""
        }
        MatchRetrofit.service.getMatch(matchRequest)
            .commHandler(mView, true, {
                mView?.showMatchList(it)
            })
    }

    /**
     * 平台转账 跳转h5
     */
    override fun platformTransferToH5(userName: String, amount: String, menusId: String) {
        //先刷新菜单栏目 可能token失效了
        MatchRetrofit.service.getMenus().commHandler(mView, true, {
            it.filter { tab ->
                //过滤掉除滚球 今日 早盘 串关 以外的数据
                tab.menuType == 1 || tab.menuType == 3 || tab.menuType == 4 || tab.menuType == 11
            }.let { tabList ->
                mView?.setMenusListData(tabList, true)
            }
            if (amount.toDouble() > 0) {
                UserRetrofit.service.platformTransfer(userName, "1", amount)
                    .commHandler(mView, false, {
                        UserManager.get().setBalance(0.0)
                        mView?.transferSuccess(menusId)
                    }, { _, _ ->
                        mView?.transferSuccess(menusId)
                    })
            } else {
                mView?.transferSuccess(menusId)
            }
        }, { msg, _ ->
            mView?.showMsg(msg)
        })

//        UserRetrofit.service.checkBalance(userName).commHandler(mView, false, {
//            if (it > 0) {
//                UserRetrofit.service.platformTransfer(userName, "1", it.toString())
//                    .commHandler(mView, false, {
//                        mView?.transferSuccess(menusId)
//                    }, { _, _ ->
//                        mView?.transferSuccess(menusId)
//                    })
//            } else {
//                mView?.transferSuccess(menusId)
//            }
//        })
//        UserRetrofit.service.checkBalance(userName)
//            .flatMap {
//                UserRetrofit.service.platformTransfer(userName, "1", it.data)
//            }.commHandler(mView, false, {
//                mView?.transferSuccess()
//            }, { _, _ ->
//                mView?.transferSuccess()
//            })
    }

}