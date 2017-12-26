package com.xujl.mvpllirary.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.xujl.applibrary.db.bean.ImageBean;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.json.ImagePassBean;
import com.xujl.utilslibrary.view.ViewTool;
import com.xujl.widgetlibrary.adapter.BaseRecyclerViewAdapter;
import com.xujl.widgetlibrary.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujl on 2017/12/26.
 */

public class ShowImagesAdapter extends BaseRecyclerViewAdapter<ImagePassBean> {
    public ShowImagesAdapter (ArrayList<ImagePassBean> data) {
        super(R.layout.item_show_images, data);
    }

    @Override
    protected void convert (BaseViewHolder helper, ImagePassBean item) {
        if(!ViewTool.isEmpty(item.getImageUrl())){
            ImageLoader.loadImage(item.getImageUrl(), (ImageView) helper.getView(R.id.iv));
        }
    }
}
