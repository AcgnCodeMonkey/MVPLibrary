package com.xujl.mvpllirary.mvp.presenter;

import android.os.Bundle;
import android.webkit.WebView;

import com.just.library.ChromeClientCallbackManager;
import com.xujl.applibrary.mvp.presenter.CommonFragmentPresenter;
import com.xujl.applibrary.mvp.view.CommonView;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.mvp.common.WebViewHelper;
import com.xujl.mvpllirary.util.IntentKey;

/**
 * Created by xujl on 2017/9/6.
 * 资讯详情网页，使用非mvp模式
 */
public class NewsDetailFragmentPresenter extends CommonFragmentPresenter implements ChromeClientCallbackManager.ReceivedTitleCallback {
    public static NewsDetailFragmentPresenter newInstance (Bundle bundle) {
        NewsDetailFragmentPresenter fragment = new NewsDetailFragmentPresenter();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public boolean isMVP () {
        return false;
    }

    @Override
    public int getLayoutId () {
        return R.layout.fragment_news_detail;
    }

    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        getPresenterHelper().addHelper(
                new WebViewHelper(this, new WebView(exposeContext()), R.id.activity_news_detail_rootView));
        getWebViewHelper().setTitleCallback(this);
        getWebViewHelper().goWeb(getUrl());
    }

    @Override
    public boolean enableToolBar () {
        return true;
    }

    private String getUrl () {
        final Bundle bundle = getArguments();
        return bundle == null ? null : bundle.getString(IntentKey.URL);
    }

    private WebViewHelper getWebViewHelper () {
        return getPresenterHelper().getHelper(WebViewHelper.class);
    }

    @Override
    public void onReceivedTitle (WebView view, String title) {
        ((CommonView) mView).getToolBarModule().setTitle(title);
    }
}