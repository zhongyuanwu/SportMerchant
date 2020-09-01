package com.example.base

import android.content.Context
import android.view.Gravity
import com.example.base.ext.showToast
import com.example.base.widget.KProgressHUDPop

/**
 * created by jump on 2020/8/21
 * describe:
 */
abstract class BaseMvpActivity<in V : IView, P : IPresenter<V>> : BaseActivity(), IView {
    protected var mKProgressHUD: KProgressHUDPop? = null
    /**
     * Presenter
     */
    protected var mPresenter: P? = null

    protected abstract fun createPresenter(): P

    override fun initView() {
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
        this.mPresenter = null
    }

    override fun showLoading() {
        if (mKProgressHUD != null && mKProgressHUD!!.isShowing) {
            return
        }
        if (mKProgressHUD == null) {
            mKProgressHUD = KProgressHUDPop(this)
            mKProgressHUD!!.setAlignBackgroundGravity(Gravity.CENTER)
        }
        mKProgressHUD?.showPopupWindow()
    }

    override fun hideLoading() {
        mKProgressHUD?.dismiss()
    }

    override fun showError(errorMsg: String) {
        showToast(errorMsg)
    }

    override fun showDefaultMsg(msg: String) {
        showToast(msg)
    }

    override fun showMsg(msg: String) {
        showToast(msg)
    }

}