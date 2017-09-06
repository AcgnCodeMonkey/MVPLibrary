package com.xujl.mvpllirary.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.json.HomeNewsPayload;
import com.xujl.utilslibrary.data.ListUtils;
import com.xujl.widgetlibrary.adapter.BaseRecyclerViewAdapter;
import com.xujl.widgetlibrary.util.ImageLoader;

import java.util.List;

/**
 * Created by xujl on 2017/9/6.
 */

public class NewsTabAdapter extends BaseRecyclerViewAdapter<HomeNewsPayload.News> {
    public NewsTabAdapter (List<HomeNewsPayload.News> data) {
        super(R.layout.item_news, data);
    }

    @Override
    protected void convert (BaseViewHolder helper, HomeNewsPayload.News item) {
        helper.setText(R.id.item_news_titleTV, item.desc)
                .setText(R.id.item_news_whoTV, item.who)
                .setText(R.id.item_news_dateTV, item.createdAt);
        if (ListUtils.isEmpty(item.images)) {
            helper.setVisible(R.id.item_news_titleImageIV, false);
        } else {
            helper.setVisible(R.id.item_news_titleImageIV, true);
            ImageLoader.loadImage(item.images.get(0), (ImageView) helper.getView(R.id.item_news_titleImageIV));
        }


    }
}
