package com.example.sport_merchant.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.BaseMvpFragment
import com.example.sport_merchant.R
import com.example.sport_merchant.constant.Constant
import com.example.sport_merchant.constant.Constant.WEB_TO_MAIN_REQUEST_CODE
import com.example.sport_merchant.constant.UserManager
import com.example.sport_merchant.ext.format
import com.example.sport_merchant.ext.setSportIcon
import com.example.sport_merchant.http.bean.MatchBean
import com.example.sport_merchant.ui.GameHomeActivity
import com.example.sport_merchant.ui.adapter.MatchListAdapter
import com.example.sport_merchant.ui.contract.HomeContract
import com.example.sport_merchant.ui.presenter.HomePresenter
import com.example.sport_merchant.widget.EmptyView
import com.example.sport_merchant.widget.SlidingVerticalTabLayout
import com.panda.flycotablayout.TabEntity
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * created by jump on 2020/8/22
 * describe:
 */
class HomeFragment : BaseMvpFragment<HomeContract.View, HomeContract.Presenter>(),
    HomeContract.View {
    private lateinit var matchAdapter: MatchListAdapter
    private var position: Int = 0//当前被选中的第一级tab
    private var subPosition: Int = 0//当前二级菜单tab
    private var mgt = ""//早盘和串关有三级菜单

    //第一级tab
    private var tabMenusList: List<TabEntity>? = null

    //第二级子tab
    private var subMenusList: List<TabEntity>? = null

    override fun createPresenter(): HomeContract.Presenter = HomePresenter()

    override fun attachLayoutRes(): Int = R.layout.fragment_home

    @SuppressLint("SetTextI18n")
    override fun lazyLoad() {
        tv_userName.text = UserManager.get().getUsername()
        tv_user_balance.text = "￥${UserManager.get().getBalance().format()}"

        ry_match.run {
            layoutManager = LinearLayoutManager(context)
        }

        matchAdapter = MatchListAdapter(R.layout.item_game_match).apply {
            bindToRecyclerView(ry_match)
            emptyView = context?.let { EmptyView(it) }?.apply { show("暂无赛事") }
            setOnItemClickListener { adapter, view, itemPosition ->

                val menuType = tabMenusList?.get(position)?.menuType
                mgt = if (menuType == 11 || menuType == 4) {//早盘和串关
                    matchAdapter.data[itemPosition].mgt//开赛时间
                } else ""
                val menuId = subMenusList?.get(tab_layout.getCurrentPosition())?.menuId
                mPresenter?.platformTransferToH5(
                    UserManager.get().getUsername(),
                    UserManager.get().getBalance().toString(),
                    menuId.toString()
                )
            }
            addFooterView(getFooterView(View.OnClickListener {
                val menuId = subMenusList?.get(tab_layout.getCurrentPosition())?.menuId
                mPresenter?.platformTransferToH5(
                    UserManager.get().getUsername(),
                    UserManager.get().getBalance().toString(),
                    menuId.toString()
                )
            }))
        }

        rg_container.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_one -> {
                    position = 0
                    val one = tabMenusList?.get(0)
                    subMenusList = one?.subList?.toMutableList()?.setSportIcon()
                    if (subMenusList != null && subMenusList?.isNotEmpty()!!) {
                        mPresenter?.getMatchList(
                            one?.menuType.toString(),
                            subMenusList?.get(0)?.menuId.toString()
                        )
                    }
                }
                R.id.rb_two -> {
                    position = 1
                    val two = tabMenusList?.get(1)
                    subMenusList = two?.subList?.toMutableList()?.setSportIcon()
                    if (subMenusList != null && subMenusList?.isNotEmpty()!!) {
                        mPresenter?.getMatchList(
                            two?.menuType.toString(),
                            subMenusList?.get(0)?.menuId.toString()
                        )
                    }
                }
                R.id.rb_three -> {
                    position = 2
                    val three = tabMenusList?.get(2)
                    subMenusList = three?.subList?.toMutableList()?.setSportIcon()
                    if (subMenusList != null && subMenusList?.isNotEmpty()!!) {
                        mPresenter?.getMatchList(
                            three?.menuType.toString(),
                            subMenusList?.get(0)?.menuId.toString()

                        )
                    }
                }
                R.id.rb_four -> {
                    position = 3
                    val four = tabMenusList?.get(3)
                    subMenusList = four?.subList?.toMutableList()?.setSportIcon()
                    if (subMenusList != null && subMenusList?.isNotEmpty()!!) {
                        mPresenter?.getMatchList(
                            four?.menuType.toString(),
                            subMenusList?.get(0)?.menuId.toString()

                        )
                    }
                }
            }
            if (subMenusList == null || subMenusList?.isEmpty()!!) {
                tab_layout.visibility = View.INVISIBLE
            } else {
                tab_layout.visibility = View.VISIBLE
                tab_layout.setTabs(subMenusList!!)
            }
        }

        tab_layout.setOnTabSelectListener(object : SlidingVerticalTabLayout.OnTabSelectListener {
            override fun onTabSelect(subPosition: Int) {
                val subTab = subMenusList?.get(subPosition)
                mPresenter?.getMatchList(
                    tabMenusList?.get(position)?.menuType.toString(),
                    subTab?.menuId.toString()
                )
            }
        })
        mPresenter?.checkBalance(UserManager.get().getUsername())
        mPresenter?.loginPanda(UserManager.get().getUsername())
    }

    private fun getFooterView(listener: View.OnClickListener): View? {
        val view = layoutInflater.inflate(R.layout.footer_view, ry_match, false)
        view.setOnClickListener(listener)
        return view
    }

    override fun updateBalance(balance: Double) {
        tv_user_balance.text = "￥${balance.format()}"
        UserManager.get().setBalance(balance)
    }

    override fun setMenusListData(tabEntity: List<TabEntity>, refreshCount: Boolean) {
        if (tabEntity.isEmpty()) {
            tab_layout.visibility = View.INVISIBLE
        } else {
            tabMenusList = tabEntity
            rb_one.text = tabEntity[0].menuName + tabEntity[0].count
            rb_two.text = tabEntity[1].menuName + tabEntity[1].count
            rb_three.text = tabEntity[2].menuName + tabEntity[2].count
            rb_four.text = tabEntity[3].menuName + tabEntity[3].count
            if (refreshCount) return
            subMenusList = tabEntity[0].subList.toMutableList().setSportIcon()
            if (subMenusList == null || subMenusList!!.isEmpty()) {
                tab_layout.visibility = View.INVISIBLE
            } else {
                tab_layout.visibility = View.VISIBLE
                tab_layout.setTabs(subMenusList!!)
            }
        }
    }

    override fun showMatchList(matchBean: List<MatchBean>) {
        matchAdapter.setNewData(matchBean)
    }

    //平台转账成功 跳转h5
    override fun transferSuccess(menusId: String) {
        tv_user_balance.text = "￥${UserManager.get().getBalance().format()}"
        val intent = Intent(context, GameHomeActivity::class.java)
        val bundle = Bundle()
        if (menusId.isNotEmpty()) {
            val parentId = menusId.substring(0, 3)
            bundle.putString("parentId", parentId)
            val childId = menusId.substring(3, menusId.length)
            bundle.putString("childId", childId)
            bundle.putString("mgt", mgt)
        }
        intent.putExtras(bundle)
        startActivityForResult(intent, WEB_TO_MAIN_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Constant.WEB_TO_MAIN_RESULT_CODE) {
//            data?.getStringExtra("balance")
            mPresenter?.checkBalance(UserManager.get().getUsername())
        }
    }

    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


}