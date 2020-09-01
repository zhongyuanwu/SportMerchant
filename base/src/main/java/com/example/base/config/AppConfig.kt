package com.example.base.config

import android.app.Application

/**
 * created by jump on 2020/8/21
 * describe:
 */
object AppConfig {

    const val TAG = "KotlinMVP"

    var debug = false

    private var application: Application? = null

    /**
     * Init, it must be call before used .
     */
    fun init(application: Application) {
        this.application = application
    }

    fun getApplication(): Application {
        if (application == null) {
            throw RuntimeException("Please init in Application#onCreate first.")
        }
        return application!!
    }

    fun openDebug() {
        debug = true
    }

}