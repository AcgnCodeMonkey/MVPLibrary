package com.xujl.mvpllirary.mvp.presenter;

import android.os.Bundle;
import android.webkit.WebView;

import com.just.library.ChromeClientCallbackManager;
import com.xujl.applibrary.mvp.presenter.CommonActivityPresenter;
import com.xujl.applibrary.mvp.view.CommonView;
import com.xujl.baselibrary.mvp.port.HelperType;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.mvp.common.WebViewHelper;
import com.xujl.mvpllirary.util.IntentKey;

/**
 * Created by xujl on 2017/9/6.
 * 资讯详情网页，使用非mvp模式
 */
public class NewsDetailActivityPresenter extends CommonActivityPresenter implements ChromeClientCallbackManager.ReceivedTitleCallback {
    @Override
    protected Class<?> getViewClassType () {
        return null;
    }

    @Override
    protected Class<?> getModelClassType () {
        return null;
    }

    @Override
    public boolean isMVP () {
        return false;
    }

    @Override
    public int getLayoutId () {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        getPresenterHelper().addHelper(HelperType.TYPE_ONE,
                new WebViewHelper(this, new WebView(exposeContext()), R.id.activity_news_detail_rootView));
        getWebViewHelper().setTitleCallback(this);
        getWebViewHelper().goWeb(getUrl());
    }

    private String getUrl () {
        final Bundle bundle = getIntent().getExtras();
        return bundle == null ? null : bundle.getString(IntentKey.URL);
    }

    private WebViewHelper getWebViewHelper () {
        return getPresenterHelper().getHelper(HelperType.TYPE_ONE);
    }

    @Override
    public void onReceivedTitle (WebView view, String title) {
        ((CommonView) mView).getToolBarModule().setTitle(title);
    }
}