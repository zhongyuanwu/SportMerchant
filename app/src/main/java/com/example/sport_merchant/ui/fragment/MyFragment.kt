package com.example.sport_merchant.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.base.ActivityManager
import com.example.base.BaseFragment
import com.example.sport_merchant.R
import com.example.sport_merchant.constant.UserManager
import com.example.sport_merchant.event.refreshBalanceEvent
import com.example.sport_merchant.ext.format
import com.example.sport_merchant.ui.LoginActivity
import com.example.sport_merchant.ui.OrderRecordActivity
import kotlinx.android.synthetic.main.fragment_my.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * created by jump on 2020/8/22
 * describe:
 */
class MyFragment : BaseFragment() {
    override fun attachLayoutRes(): Int = R.layout.fragment_my
    override fun useEventBus(): Boolean =true
    override fun initView(view: View) {
        tv_bet_record.setOnClickListener {
            startActivity(Intent(context, OrderRecordActivity::class.java))
        }
    }

    override fun lazyLoad() {
        tv_userName.text = UserManager.get().getUsername()
        tv_balance.text = UserManager.get().getBalance().toString()
        tv_out_login.setOnClickListener {
            context?.let { it1 -> UserManager.get().outLogin(it1) }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun refreshBalance(event: refreshBalanceEvent){
        tv_balance.text = UserManager.get().getBalance().format()
    }

    companion object {
        fun newInstance(): MyFragment {
            val args = Bundle()

            val fragment = MyFragment()
            fragment.arguments = args
            return fragment
        }
    }
}