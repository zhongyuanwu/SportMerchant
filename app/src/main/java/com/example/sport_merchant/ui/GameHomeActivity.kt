package com.example.sport_merchant.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.KeyEvent
import android.webkit.JavascriptInterface
import android.webkit.ValueCallback
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.core.math.MathUtils
import com.example.base.BaseActivity
import com.example.base.http.constant.HttpConstant
import com.example.base.utils.GsonUtils
import com.example.base.utils.MathUtils.isNumeric
import com.example.base.utils.SchedulerUtils
import com.example.sport_merchant.R
import com.example.sport_merchant.constant.Constant.WEB_TO_MAIN_RESULT_CODE
import com.example.sport_merchant.constant.UserManager
import com.example.sport_merchant.http.UserRetrofit
import com.example.sport_merchant.http.bean.WebViewBean
import com.just.agentweb.AgentWeb
import com.just.agentweb.DefaultWebClient
import com.just.agentweb.WebChromeClient
import com.just.agentweb.WebViewClient
import kotlinx.android.synthetic.main.activity_game_home.*

/**
 * created by jump on 2020/8/24
 * describe: 游戏主页 webview 展示h5
 * http://test-user-h5-bw2.sportxxxifbdxm2.com/?m=200&s=03&token=7433d83a815795cb3d5b4e53b038235056996fef
 */
class GameHomeActivity : BaseActivity() {
    //http://172.18.180.32:8080/?m=200&s=04&token=66b603e5f82868aa06bc00b81ab15087367edca2#/match
    private var host: String = "http://172.18.180.32:8080/"       //HttpConstant.DOMAIN
    private lateinit var mAgentWeb: AgentWeb
    override fun attachLayoutRes(): Int = R.layout.activity_game_home

    override fun initView() {
        val parentId = intent.extras?.getString("parentId")
        val childId = intent.extras?.getString("childId")
        val mgt = intent.extras?.getString("mgt")
        //早盘和串关需要传时间过去

        val time = if (mgt?.isNotEmpty()!!) "&t=$mgt" else ""
        val url =
            HttpConstant.DOMAIN + "?m=" + parentId + "&s=" + childId + time + "&y=android&token=" + HttpConstant.TOKEN
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(container, FrameLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .setWebChromeClient(WebChromeClient())
            .setWebViewClient(webViewClient)
            .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
            .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK) //打开其他应用时，弹窗咨询用户是否前往其他应用
            .interceptUnkownUrl() //拦截找不到相关页面的Scheme
            .createAgentWeb()
            .ready()
            .go(url)
        //注入对象
        mAgentWeb.jsInterfaceHolder.addJavaObject("android", AndroidInterface(this))
    }

    private val webViewClient: WebViewClient by lazy {
        object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                Log.e("avg", "onPageStarted------url:$url")
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun transfer(balance: String) {
        // transferType 1 上分(欧宝减钱,panda 加钱) 2 下分 (相反)
        UserRetrofit.service.platformTransfer(UserManager.get().getUsername(), "2", balance)
            .compose(SchedulerUtils.ioToMain())
            .subscribe {
                val intent = Intent()
                intent.putExtra("balance", balance)
                setResult(WEB_TO_MAIN_RESULT_CODE, intent)
                finish()
            }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean =
        if (mAgentWeb.handleKeyEvent(keyCode, event)) true else {
            mAgentWeb.jsAccessEntrace.quickCallJs("callByAndroid", ValueCallback {
                if (!isNumeric(it) || it.toDouble() == 0.0) finish() else transfer(it)
            })
            true
        }

    override fun onPause() {
        mAgentWeb.webLifeCycle.onPause()
        super.onPause()
    }

    override fun onResume() {
        mAgentWeb.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mAgentWeb.clearWebCache()
        super.onDestroy()
        mAgentWeb.webLifeCycle.onDestroy()
    }

    class AndroidInterface(private val activity: GameHomeActivity) {
        private val deliver = Handler(Looper.getMainLooper())

        @JavascriptInterface
        fun callAndroid(msg: String) {
            Log.e("avg", "------------msg:$msg")
            if (!msg.contains("{")) return
            deliver.post {
                val fromJson = GsonUtils.fromJson(msg, WebViewBean::class.java)
                when (fromJson.code) {
                    "0" -> { //code=0 返回
                        if (fromJson.data.toDouble() == 0.0) {
                            activity.finish()
                        } else {
                            activity.transfer(fromJson.data)
                        }

                    }
                    "1" -> {//code=1 token失效
                        UserManager.get().outLogin(activity)
                    }
                }
            }

        }

    }

}