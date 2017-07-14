package com.xujl.applibrary.db;


import com.xujl.applibrary.db.bean.ImageBean;
import com.xujl.applibrary.db.bean.ImageBeanDao;
import com.xujl.applibrary.util.AppApplication;
import com.xujl.utilslibrary.view.ViewTool;

import java.util.List;

/**
 * Created by xujl on 2017/3/23.
 */

public class DBUtils {
    /**
     * 添加数据，如果有重复则覆盖
     */
    public static void insert (ImageBean bean) {
        ImageBean imageBean = queryForImageId(bean.getImageId());
        if (imageBean != null && imageBean.getImageId().equals(bean.getImageId())) {
            imageBean.setImagePath(bean.getImagePath());
            imageBean.setImageUrl(bean.getImageUrl());
            imageBean.setCreatDate(bean.getCreatDate());
            imageBean.setType(bean.getType());
            delete(imageBean.getId());
        } else {
            imageBean = bean;
        }
        AppApplication.getDaoInstant().getImageBeanDao().insert(imageBean);

    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void delete (long id) {
        AppApplication.getDaoInstant().getImageBeanDao().deleteByKey(id);
    }

    /**
     * 更新数据
     */
    public static void update (ImageBean bean) {
        if (bean == null) {
            return;
        }
        AppApplication.getDaoInstant().getImageBeanDao().update(bean);
    }

    public static void clearDb () {
        AppApplication.getDaoInstant().getImageBeanDao().deleteAll();
    }

    /**
     * 查询某个网络图片的所有对象
     *
     * @return
     */
    public static ImageBean queryForUrl (String imageUrl) {
        if (ViewTool.isEmpty(imageUrl)) {
            return null;
        }
        return AppApplication.getDaoInstant().getImageBeanDao().queryBuilder()
                .where(ImageBeanDao.Properties.ImageUrl.eq(imageUrl)).unique();
    }

    /**
     * 通过本地路径查询某个已下载的图片
     *
     * @return
     */
    public static ImageBean queryForPath (String imagePath) {
        if (ViewTool.isEmpty(imagePath)) {
            return null;
        }
        return AppApplication.getDaoInstant().getImageBeanDao().queryBuilder()
                .where(ImageBeanDao.Properties.ImagePath.eq(imagePath)).unique();
    }

    /**
     * 通查询某个type的图片的所有对象，图片类型：0已收藏，1已下载，2已收藏并且已下载
     * 时间倒序排列
     *
     * @return
     */
    public static List<ImageBean> queryForType (int type) {
        return AppApplication.getDaoInstant().getImageBeanDao().queryBuilder()
                .where(ImageBeanDao.Properties.Type.eq(type)).orderDesc(ImageBeanDao.Properties.CreatDate).list();
    }

    /**
     * imageId查询所有数据（非数据id）
     */
    public static ImageBean queryForImageId (String imageId) {
        if (ViewTool.isEmpty(imageId)) {
            return null;
        }
        return AppApplication.getDaoInstant().getImageBeanDao().queryBuilder()
                .where(ImageBeanDao.Properties.ImageId.eq(imageId)).unique();
    }

    /**
     * 查询全部数据，时间倒序排列
     */
    public static List<ImageBean> queryAll () {
        return AppApplication.getDaoInstant().getImageBeanDao().queryBuilder().orderDesc(ImageBeanDao.Properties.CreatDate).list();
    }

}
