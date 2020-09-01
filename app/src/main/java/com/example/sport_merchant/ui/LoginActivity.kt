package com.example.sport_merchant.ui

import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.base.BaseMvpActivity
import com.example.base.ext.setSingleClickListener
import com.example.base.utils.SPStaticUtils
import com.example.sport_merchant.R
import com.example.sport_merchant.constant.UserManager
import com.example.sport_merchant.http.bean.UserInfo
import com.example.sport_merchant.ui.contract.LoginContract
import com.example.sport_merchant.ui.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*

/**
 * created by jump on 2020/8/21
 * describe:
 */
class LoginActivity : BaseMvpActivity<LoginContract.View, LoginContract.Presenter>(),
    LoginContract.View {
    override fun createPresenter(): LoginContract.Presenter = LoginPresenter()

    override fun attachLayoutRes(): Int = R.layout.activity_login
    override fun initView() {
        super.initView()
        et_userName.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) et_userName.setSelection(et_userName.text.length)
        }
    }

    override fun initData() {
        super.initData()
        tv_login.setSingleClickListener {
            val username = et_userName.text.toString()
            val password = et_passWord.text.toString()
            if (TextUtils.isEmpty(username)) {
                showDefaultMsg("账号不能为空")
                return@setSingleClickListener
            }
            if (TextUtils.isEmpty(password)) {
                showDefaultMsg("密码不能为空")
                return@setSingleClickListener
            }
            mPresenter?.login(username, password)
        }
    }


    override fun loginSuccess(userInfo: UserInfo) {
        SPStaticUtils.put("isLogin",true)
        UserManager.get().saveUser(userInfo)
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun loginFail(msg:String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}