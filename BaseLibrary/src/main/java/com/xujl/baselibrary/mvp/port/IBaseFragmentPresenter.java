package com.xujl.baselibrary.mvp.port;

import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Created by xujl on 2017/12/25.
 */

public interface IBaseFragmentPresenter extends IBasePresenter,ISupportFragment {
    /**
     * 退出当前fragment
     */
    void exitFragment();

    /**
     * 设置返回码
     * @param resultCode
     */
    void setFragmentResult(int resultCode);
}
