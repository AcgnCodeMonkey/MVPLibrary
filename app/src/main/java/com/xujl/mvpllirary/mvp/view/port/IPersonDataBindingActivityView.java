package com.xujl.mvpllirary.mvp.view.port;

import com.xujl.applibrary.mvp.port.ICommonView;
import com.xujl.mvpllirary.json.PersonPayload;

/**
 * Created by xujl on 2017/9/12.
 */
public interface IPersonDataBindingActivityView extends ICommonView {
    void showResult(String result);

    void setPerson (PersonPayload personPayload);
}