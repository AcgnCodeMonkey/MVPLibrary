package com.xujl.mvpllirary.mvp.common;

import android.app.Activity;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;
import com.xujl.applibrary.mvp.presenter.CommonFragmentPresenter;
import com.xujl.baselibrary.mvp.common.BaseHelper;
import com.xujl.baselibrary.mvp.port.IBaseView;

import androidx.fragment.app.Fragment;

/**
 * Created by xujl on 2017/9/6.
 */

public class WebViewHelper extends BaseHelper {
    private AgentWeb mAgentWeb;
    private AgentWeb.PreAgentWeb preAgentWeb;
    private Activity mActivity;
    private Fragment mFragment;
    private WebView mWebView;
    private WebViewClient webViewClient;
    private ChromeClientCallbackManager.ReceivedTitleCallback callback;
    private int parentId;

    public WebViewHelper (Activity activity, WebView webView, int parentId) {
        mActivity = activity;
        mWebView = webView;
        this.parentId = parentId;
    }

    public WebViewHelper (Fragment fragment, WebView webView, int parentId) {
        mFragment = fragment;
        mWebView = webView;
        this.parentId = parentId;
    }

    public void setWebViewClient (WebViewClient webViewClient) {
        this.webViewClient = webViewClient;
    }

    public void setTitleCallback (ChromeClientCallbackManager.ReceivedTitleCallback callback) {
        this.callback = callback;
    }

    public void goWeb (String url) {
        if (preAgentWeb == null || mAgentWeb == null) {
            initAgentWeb();
            mAgentWeb = preAgentWeb.go(url);
        }
        mAgentWeb.getLoader().loadUrl(url);
    }

    public AgentWeb getAgentWeb () {
        if (preAgentWeb == null) {
            initAgentWeb();
            mAgentWeb = preAgentWeb.go("");
        }
        return mAgentWeb;
    }

    private void initAgentWeb () {
        if (mActivity != null) {
            preAgentWeb = AgentWeb.with(mActivity)
                    .setAgentWebParent((ViewGroup) mActivity.findViewById(parentId),
                            new LinearLayout.LayoutParams(-1, -1))
                    .useDefaultIndicator()// 使用默认进度条
                    .defaultProgressBarColor() // 使用默认进度条颜色
                    .setReceivedTitleCallback(callback) //设置 Web 页面的 title 回调
                    .setWebViewClient(webViewClient)
                    .setWebView(mWebView)
                    .createAgentWeb()//
                    .ready();
            //传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams;
        } else {
            final IBaseView baseView = ((CommonFragmentPresenter) mFragment).exposeView();
            preAgentWeb = AgentWeb.with(mFragment)
                    .setAgentWebParent((ViewGroup) baseView.findView(parentId),
                            new LinearLayout.LayoutParams(-1, -1)).useDefaultIndicator()
//                    .useDefaultIndicator()// 使用默认进度条
//                    .defaultProgressBarColor() // 使用默认进度条颜色
                    .setReceivedTitleCallback(callback) //设置 Web 页面的 title 回调
                    .setWebViewClient(webViewClient)
                    .setWebView(mWebView)
                    .createAgentWeb()//
                    .ready();
            ;
            //传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams;;
        }
    }
}
