package com.example.sport_merchant.ext

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import com.example.base.ActivityManager
import com.example.base.BaseEntity
import com.example.base.IView
import com.example.base.config.AppConfig
import com.example.base.http.constant.HttpConstant
import com.example.base.utils.SchedulerUtils
import com.example.sport_merchant.widget.popup.TokenExpiredPopup
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

/**
 * created by jump on 2020/8/21
 * describe:
 */

//网络请求统一处理 要使用这个 当前presenter 宿主必须实现了BaseView
@SuppressLint("CheckResult")
fun <T> Observable<BaseEntity<T>>.commHandler(view: IView?,
                                              isShowLoading: Boolean = true,
                                              onSuccess: (T) -> Unit,
                                              onError: ((String, String) -> Unit)? = null): Disposable {
    if (isShowLoading) view?.showLoading()
    return compose(SchedulerUtils.ioToMain())
        .subscribe({
            if (isShowLoading) view?.hideLoading()
            when {
                it.code == HttpConstant.SUCCESS_CODE -> onSuccess.invoke(it.data!!)
                it.code =="0000"->onSuccess.invoke(it.data!!)
                it.code == HttpConstant.LOCAL_TOKEN_INVALID_CODE->{
                    //token 过期重新登录
                    ActivityManager.get().currentActivity()?.let { it1 ->TokenExpiredPopup(it1).showPopupWindow()}
                }
                else -> {
                    if (it.msg!!.isNotEmpty()) {
                        onError?.invoke(it.msg!!, it.code!!)
                    }
                }
            }
        }, {
            view?.hideLoading()
            onError?.invoke("请求失败，请稍后重试", "999")
        })
}