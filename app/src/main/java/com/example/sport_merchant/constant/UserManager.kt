package com.example.sport_merchant.constant

import android.content.Context
import android.content.Intent
import com.example.base.ActivityManager
import com.example.base.utils.GsonUtils
import com.example.base.utils.SPStaticUtils
import com.example.sport_merchant.http.bean.UserInfo
import com.example.sport_merchant.ui.LoginActivity

/**
 * created by jump on 2020/8/23
 * describe:
 */
class UserManager private constructor() {
    private var userInfo: UserInfo? =null

    fun getToken(): String = userInfo?.token?:""

    fun getUsername(): String = userInfo?.username?:""

    fun getBalance(): Double = userInfo?.balance?:0.00

    fun setBalance(balance:Double){
        userInfo?.balance=balance
    }
    fun saveUser(userInfo: UserInfo) {
        this.userInfo = userInfo
        val toJson = GsonUtils.toJson(userInfo)
        SPStaticUtils.put("userSpKey", toJson)
    }

    fun outLogin(context: Context){
        SPStaticUtils.put("isLogin",false)
        SPStaticUtils.put("userSpKey", "")
        val intent=Intent(context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)//必须创建新栈
        context.startActivity(intent)
    }

    companion object {
        private var instance: UserManager? = null
            get() {
                if (field == null) {
                    field = UserManager()
                }
                return field
            }

        fun get(): UserManager {
            if(instance?.userInfo==null){
                instance?.userInfo= GsonUtils.fromJson(SPStaticUtils.getString("userSpKey"), UserInfo::class.java)
            }
            return instance!!
        }
    }
}