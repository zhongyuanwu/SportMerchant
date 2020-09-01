package com.example.sport_merchant.ui

import android.view.KeyEvent
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.base.ActivityManager
import com.example.base.BaseMvpActivity
import com.example.sport_merchant.R
import com.example.sport_merchant.http.bean.UserInfo
import com.example.sport_merchant.ui.contract.MainContract
import com.example.sport_merchant.ui.fragment.HomeFragment
import com.example.sport_merchant.ui.fragment.MyFragment
import com.example.sport_merchant.ui.presenter.MainPresenter
import com.example.sport_merchant.widget.TabBottomEntity
import com.panda.flycotablayout.listener.CustomTabEntity
import com.panda.flycotablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter>(),
    MainContract.View {
    private var homeFragment: HomeFragment? = null
    private var myFragment: MyFragment? = null
    override fun createPresenter(): MainContract.Presenter = MainPresenter()

    override fun attachLayoutRes(): Int = R.layout.activity_main

    override fun initView() {
        super.initView()
        val tabList = ArrayList<CustomTabEntity>()
        val homeTab =
            TabBottomEntity("首页", R.mipmap.icon_tab_home_sel, R.mipmap.icon_tab_home_normal)
        val myTab = TabBottomEntity("我的", R.mipmap.icon_tab_mine_sel, R.mipmap.icon_tab_mine_normal)
        tabList.add(homeTab)
        tabList.add(myTab)
        tab_bottom.setTabData(tabList)
        tab_bottom.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {

            }

        })
        switchFragment(0)
    }

    private fun switchFragment(position: Int) {
        supportFragmentManager.beginTransaction().apply {
            homeFragment ?: let {
                HomeFragment.newInstance().let {
                    homeFragment = it
                    add(R.id.fl_container, it)
                }
            }
            myFragment ?: let {
                MyFragment.newInstance().let {
                    myFragment = it
                    add(R.id.fl_container, it)
                }
            }
            hideFragment(this)
            when (position) {
                0 -> {
                    homeFragment?.let {
                        show(it)
                    }
                }
                1 -> {
                    myFragment?.let {
                        show(it)
                    }
                }
            }
        }.commit()
    }

    private fun hideFragment(transaction: FragmentTransaction) {
        homeFragment?.let {
            transaction.hide(it)
        }
        myFragment?.let {
            transaction.hide(it)
        }
    }

    override fun updateUserInfo(userInfo: UserInfo) {
//        UserManager.get().saveUser(userInfo)
    }

    private var exitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                ActivityManager.get().removeAllActivity()
                exitProcess(0)
            }
            return true
        }
        return super.onKeyDown(keyCode, event);
    }
}