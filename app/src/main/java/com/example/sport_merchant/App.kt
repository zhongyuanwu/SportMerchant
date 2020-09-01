package com.example.sport_merchant

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.base.config.AppConfig
import com.example.base.utils.GlideUtils

/**
 * created by jump on 2020/8/21
 * describe:
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        initApp()
    }

    private fun initApp() {
        AppConfig.init(this)
        GlideUtils.init(this)
        AppConfig.openDebug()
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}