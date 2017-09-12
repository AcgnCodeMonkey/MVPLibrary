package com.xujl.mvpllirary.json;

/**
 * Created by xujl on 2017/9/12.
 */

public class PersonPayload {
    public String name;
    public String age;
    public String code;
    public String phone; // 电话
    public String sex; //
    public String marriage; //
    public String phone2; //
    public String record; //
    public String address; //
    public String addressDetail; //


    @Override
    public String toString () {
        return "姓名"+name+"\n"
                +"年龄"+age+"\n"
                +"身份证号"+code+"\n"
                +"联系电话"+phone+"\n"
                +"性别"+sex+"\n"
                +"婚姻状况"+marriage+"\n"
                +"紧急联系电话"+phone2+"\n"
                +"学历"+record+"\n"
                +"家庭地址"+address+"\n"
                +"详细地址"+addressDetail+"\n";
    }
}
