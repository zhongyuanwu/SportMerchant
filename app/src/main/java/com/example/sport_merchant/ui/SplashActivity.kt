package com.example.sport_merchant.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import com.example.base.BaseActivity
import com.example.base.http.constant.HttpConstant
import com.example.base.utils.PhoneUtils
import com.example.base.utils.SPStaticUtils
import com.example.sport_merchant.R
import com.example.sport_merchant.constant.Constant
import com.example.sport_merchant.constant.Constant.KEY_FIRST_INSTALL
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

/**
 * created by jump on 2020/8/21
 * describe:
 */
class SplashActivity : BaseActivity() {
    override fun attachLayoutRes(): Int = R.layout.activity_splash

    override fun initView() {

    }

    @SuppressLint("CheckResult")
    override fun initData() {
        super.initData()
        rxPermissions.request(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
        )
            .subscribe { granted: Boolean ->
                if (granted) { // All requested permissions are granted
                    delayStart()
                } else {
                    // At least one permission is denied
                    exitProcess(1)
                }
            }
    }

    @SuppressLint("CheckResult")
    private fun delayStart() {
        val isLogin = SPStaticUtils.getBoolean("isLogin", false)
        Observable.just("delay")
            .delay(if (isLogin) 0 else 1000.toLong(), TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (isLogin) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                finish()
            }
    }
}