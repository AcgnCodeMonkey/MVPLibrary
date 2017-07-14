package com.xujl.mvpllirary.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.xujl.mvpllirary.R;
import com.xujl.quotelibrary.adapter.BaseRecyclerViewAdapter;

import java.util.List;

/**
 * Created by xujl on 2017/7/13.
 */

public class TestAdapter extends BaseRecyclerViewAdapter<String> {
    public TestAdapter (List<String> data) {
        super(R.layout.item_test,data);
    }

    @Override
    protected void convert (BaseViewHolder helper, String item) {
        helper.setText(R.id.item_test_tv,item);
    }
}
