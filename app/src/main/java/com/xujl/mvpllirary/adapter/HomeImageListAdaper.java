package com.xujl.mvpllirary.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.json.HomeImagelistPayload;
import com.xujl.widgetlibrary.adapter.BaseRecyclerViewAdapter;
import com.xujl.widgetlibrary.util.ImageLoader;

import java.util.List;

/**
 * Created by xujl on 2017/7/6.
 */

public class HomeImageListAdaper extends BaseRecyclerViewAdapter<HomeImagelistPayload.Images> {
    public HomeImageListAdaper (List<HomeImagelistPayload.Images> data) {
        super(R.layout.item_home, data);
    }

    @Override
    protected void convert (BaseViewHolder helper, HomeImagelistPayload.Images item) {
        helper.setText(R.id.item_home_nameTV,item.desc);
        ImageLoader.loadImage( item.url, (ImageView) helper.getView(R.id.item_home_imageView));
    }
}
