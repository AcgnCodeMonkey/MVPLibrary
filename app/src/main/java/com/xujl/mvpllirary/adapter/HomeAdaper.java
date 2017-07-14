package com.xujl.mvpllirary.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.json.HomePayload;
import com.xujl.quotelibrary.adapter.BaseRecyclerViewAdapter;
import com.xujl.quotelibrary.util.ImageLoader;

import java.util.List;

/**
 * Created by xujl on 2017/7/6.
 */

public class HomeAdaper extends BaseRecyclerViewAdapter<HomePayload.Images>{
    public HomeAdaper ( List<HomePayload.Images> data) {
        super(R.layout.item_home, data);
    }

    @Override
    protected void convert (BaseViewHolder helper, HomePayload.Images item) {
        helper.setText(R.id.item_home_nameTV,item.title);
        ImageLoader.loadImage(mContext, item.imageUrl, (ImageView) helper.getView(R.id.item_home_imageView));
    }
}
