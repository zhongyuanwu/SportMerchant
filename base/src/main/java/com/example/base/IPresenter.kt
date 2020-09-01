package com.example.base

/**
 * created by jump on 2020/8/21
 * describe:
 */
interface IPresenter<in V : IView> {
    /**
     * 绑定 View
     */
    fun attachView(mView: V)

    /**
     * 解绑 View
     */
    fun detachView()
}