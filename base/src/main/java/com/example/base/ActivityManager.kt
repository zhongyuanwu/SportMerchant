package com.example.base

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import java.util.*

/**
 * created by jump on 2020/8/26
 * describe:
 */
class ActivityManager {
    private val mActivities = Stack<Activity>()

    fun addActivity(activity: Activity) {
        mActivities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        hideSoftKeyBoard(activity)
        activity.finish()
        mActivities.remove(activity)
    }

    fun removeAllActivity() {
        for (activity in mActivities) {
            hideSoftKeyBoard(activity)
            activity.finish()
        }
        mActivities.clear()
    }

    fun <T : Activity?> hasActivity(tClass: Class<T>): Boolean {
        for (activity in mActivities) {
            if (tClass.name == activity.javaClass.name) {
                return !activity.isDestroyed || !activity.isFinishing
            }
        }
        return false
    }

    private fun hideSoftKeyBoard(activity: Activity) {
        val localView = activity.currentFocus
        val imm =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (localView != null && imm != null) {
            imm.hideSoftInputFromWindow(
                localView.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    fun currentActivity(): Activity? {
        return mActivities.peek()
    }

    companion object{
        private var instance:ActivityManager?=null
            get() {
                if (field == null) {
                    field = ActivityManager()
                }
                return field
            }
        fun get(): ActivityManager {
            return ActivityManager.instance!!
        }
    }
}