package com.xujl.mvpllirary.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.xujl.applibrary.db.bean.ImageBean;
import com.xujl.mvpllirary.R;
import com.xujl.quotelibrary.adapter.BaseRecyclerViewAdapter;
import com.xujl.quotelibrary.util.ImageLoader;
import com.xujl.utilslibrary.data.DateFormat;
import com.xujl.utilslibrary.view.ViewTool;

import java.util.List;

/**
 * Created by xujl on 2017/7/6.
 */

public class ImageListAdaper extends BaseRecyclerViewAdapter<ImageBean> {
    public ImageListAdaper (List<ImageBean> data) {
        super(R.layout.item_image_list, data);
    }

    @Override
    protected void convert (BaseViewHolder helper, ImageBean item) {
        helper.setText(R.id.item_image_list_dateTV, DateFormat.getDate(item.getCreatDate() + "", "yyyy-MM-dd HH:mm"));
        if(!ViewTool.isEmpty(item.getImageUrl())){
            ImageLoader.loadImage(mContext, item.getImageUrl(), (ImageView) helper.getView(R.id.item_image_list_imageView));
        }

    }
}
