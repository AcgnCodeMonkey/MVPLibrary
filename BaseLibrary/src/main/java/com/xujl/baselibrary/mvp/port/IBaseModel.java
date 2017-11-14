package com.xujl.baselibrary.mvp.port;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃  神兽保佑
 * 　　　　┃　　　┃  代码无bug
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 * Created by xujl on 2017/4/28.
 */

public interface IBaseModel {
    /**
     * 初始化model（空实现，非必选）
     */
    void initModel(IBasePresenter presenter);
    /**
     * json转实体类
     * @param json 数据源
     * @param classOfT 实体类类型
     * @param <T> 实体类名
     * @return 返回实体对象
     */
    <T> T fromJson (String json, Class<T> classOfT);

    /**
     * object对象转json串
     * @param src 对象
     * @return json串
     */
    String toJson (Object src);
}
