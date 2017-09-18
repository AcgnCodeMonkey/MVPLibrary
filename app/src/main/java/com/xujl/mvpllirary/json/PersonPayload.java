package com.xujl.mvpllirary.json;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.xujl.mvpllirary.BR;

/**
 * Created by xujl on 2017/9/12.
 */

public class PersonPayload extends BaseObservable {
    private String name;
    private String age;
    private String code;
    private String phone; // 电话
    private String sex; //
    private String marriage; //
    private String phone2; //
    private String record; //
    private String address; //
    private String addressDetail; //
    @Bindable
    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
    @Bindable
    public String getAge () {
        return age;
    }

    public void setAge (String age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }
    @Bindable
    public String getCode () {
        return code;
    }

    public void setCode (String code) {
        this.code = code;
        notifyPropertyChanged(BR.code);
    }
    @Bindable
    public String getPhone () {
        return phone;
    }

    public void setPhone (String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }
    @Bindable
    public String getSex () {
        return sex;
    }

    public void setSex (String sex) {
        this.sex = sex;
        notifyPropertyChanged(BR.sex);
    }
    @Bindable
    public String getMarriage () {
        return marriage;
    }

    public void setMarriage (String marriage) {
        this.marriage = marriage;
        notifyPropertyChanged(BR.marriage);
    }
    @Bindable
    public String getPhone2 () {
        return phone2;
    }

    public void setPhone2 (String phone2) {
        this.phone2 = phone2;
        notifyPropertyChanged(BR.phone2);
    }
    @Bindable
    public String getRecord () {
        return record;
    }

    public void setRecord (String record) {
        this.record = record;
        notifyPropertyChanged(BR.record);
    }
    @Bindable
    public String getAddress () {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
    }
    @Bindable
    public String getAddressDetail () {
        return addressDetail;
    }

    public void setAddressDetail (String addressDetail) {
        this.addressDetail = addressDetail;
        notifyPropertyChanged(BR.addressDetail);
    }

    @Override
    public String toString () {
        return "姓名:  "+name+"\n"
                +"年龄:  "+age+"\n"
                +"身份证号:  "+code+"\n"
                +"联系电话:  "+phone+"\n"
                +"性别:  "+sex+"\n"
                +"婚姻状况:  "+marriage+"\n"
                +"紧急联系电话:  "+phone2+"\n"
                +"学历:  "+record+"\n"
                +"家庭地址:  "+address+"\n"
                +"详细地址:  "+addressDetail+"\n";
    }
}
