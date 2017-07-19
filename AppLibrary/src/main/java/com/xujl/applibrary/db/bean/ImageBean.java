package com.xujl.applibrary.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xujl on 2017/7/10.
 */
@Entity
public class ImageBean {
    @Id(autoincrement = true)
    private Long id;
    /**
     * 数据创建日期
     */
    private long creatDate;
    /**
     * 图片类型：参考ImageBeanType类
     */
    private int type;
    /**
     * 图片id
     */
    private String imageId;
    /**
     * 图片地址
     */
    private String imageUrl;
    /**
     * 图片本地路径
     */
    private String imagePath;
    @Generated(hash = 1617061106)
    public ImageBean(Long id, long creatDate, int type, String imageId,
            String imageUrl, String imagePath) {
        this.id = id;
        this.creatDate = creatDate;
        this.type = type;
        this.imageId = imageId;
        this.imageUrl = imageUrl;
        this.imagePath = imagePath;
    }
    @Generated(hash = 645668394)
    public ImageBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getCreatDate() {
        return this.creatDate;
    }
    public void setCreatDate(long creatDate) {
        this.creatDate = creatDate;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getImageId() {
        return this.imageId;
    }
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getImagePath() {
        return this.imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
