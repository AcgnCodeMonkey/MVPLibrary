package com.xujl.mvpllirary.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.json.HomeNewsPayload;
import com.xujl.widgetlibrary.adapter.BaseRecyclerViewAdapter;

import java.util.List;

/**
 * Created by xujl on 2017/9/6.
 */

public class HomeNewsAdapter extends BaseRecyclerViewAdapter<HomeNewsPayload.News> {
    public HomeNewsAdapter (List<HomeNewsPayload.News> data) {
        super(R.layout.item_news,data);
    }

    @Override
    protected void convert (BaseViewHolder helper, HomeNewsPayload.News item) {
        helper.setText(R.id.item_news_titleTV,item.desc)
                .setText(R.id.item_news_whoTV,item.who)
                .setText(R.id.item_news_dateTV,item.createdAt);
    }
}
