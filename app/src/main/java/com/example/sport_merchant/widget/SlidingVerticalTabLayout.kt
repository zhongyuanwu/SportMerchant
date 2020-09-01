package com.example.sport_merchant.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.example.base.utils.CommonUtil
import com.example.sport_merchant.R
import com.panda.flycotablayout.TabEntity
import java.util.*

/**
 * created by jump on 2020/8/22
 * describe:
 */
class SlidingVerticalTabLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ScrollView(context, attrs, defStyleAttr) {
    private var mTabsContainer: LinearLayout? = null
    private var mTabEntitys: ArrayList<TabEntity>? = null
    private var mTabCount = 0
    private var currentPosition=0
    init {
        mTabsContainer = LinearLayout(context)
        mTabsContainer!!.orientation = LinearLayout.VERTICAL
        addView(mTabsContainer)
    }

    fun setTabs(tabEntities: List<TabEntity>) {
        check(tabEntities.isNotEmpty()) { "Titles can not be EMPTY !" }
        if (mTabEntitys != null) {
            mTabEntitys!!.clear()
        } else {
            mTabEntitys = ArrayList()
        }
        mTabEntitys!!.addAll(tabEntities)
        notifyDataSetChanged()
    }

    fun updateTabs(tabEntities: List<TabEntity>){
        check(tabEntities.isNotEmpty()) { "Titles can not be EMPTY !" }

    }


    private fun notifyDataSetChanged() {
        mTabsContainer!!.removeAllViews()
        this.mTabCount = mTabEntitys!!.size
        var tabView: View
        for (i in 0 until mTabCount) {
            tabView = View.inflate(context, R.layout.layout_tab_vertical, null)
            addTab(i, mTabEntitys!![i], tabView)
        }
    }
    fun getCurrentPosition():Int=currentPosition
    private fun addTab(position: Int, tabEntity: TabEntity, tabView: View?) {
        val ivTabIcon = tabView?.findViewById<ImageView>(R.id.iv_tab_icon)
        if(position==0) {
            currentPosition=0
            ivTabIcon?.setImageResource(tabEntity.selectedIcon)
        }else{
            ivTabIcon?.setImageResource(tabEntity.unSelectedIcon)
        }
        val tvTabTitle = tabView?.findViewById<TextView>(R.id.tv_tab_title)
        tvTabTitle?.text = tabEntity.menuName
        tabView?.setOnClickListener {
            val position1 = mTabsContainer!!.indexOfChild(it)
            if (position1 != -1) {
                currentPosition=position1
                mListener?.onTabSelect(position1)
            }
            for (i in 0 until mTabCount) {
                val otherView = mTabsContainer!!.getChildAt(i)
                val data = mTabEntitys?.get(i)
                val icon = otherView.findViewById<ImageView>(R.id.iv_tab_icon)
                val title = otherView.findViewById<TextView>(R.id.tv_tab_title)
                if (position1 != i) {
                    otherView.isSelected = false
                    title.isSelected=false
                    data?.unSelectedIcon?.let { it1 -> icon.setImageResource(it1) }
                }else{
                    otherView.isSelected = true
                    title.isSelected=true
                    data?.selectedIcon?.let { it1 -> icon.setImageResource(it1) }
                }
            }
        }
        if (position == 0) tabView?.isSelected = true
        /** 每一个Tab的布局参数  */
        val lpTab = LinearLayout.LayoutParams(
            LayoutParams.MATCH_PARENT,
            CommonUtil.dp2px(context, 80f)
        )
        lpTab.leftMargin = CommonUtil.dp2px(context, 1f)
        lpTab.rightMargin = CommonUtil.dp2px(context, 1f)
        lpTab.topMargin = CommonUtil.dp2px(context, 1f)
        lpTab.bottomMargin = CommonUtil.dp2px(context, 1f)
        mTabsContainer!!.addView(tabView, position, lpTab)
    }

    private var mListener: OnTabSelectListener? = null

    fun setOnTabSelectListener(listener: OnTabSelectListener?) {
        mListener = listener
    }

    interface OnTabSelectListener {
        fun onTabSelect(position: Int)
    }
}