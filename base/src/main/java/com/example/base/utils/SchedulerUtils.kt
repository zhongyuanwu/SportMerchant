package com.example.base.utils

import com.example.base.BaseScheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * created by jump on 2020/8/21
 * describe:
 */
object SchedulerUtils {
    fun<T> ioToMain(): BaseScheduler<T> {
        return BaseScheduler(Schedulers.io(), AndroidSchedulers.mainThread())
    }
}