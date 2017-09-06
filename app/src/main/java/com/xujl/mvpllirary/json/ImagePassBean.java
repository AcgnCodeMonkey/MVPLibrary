package com.xujl.mvpllirary.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.xujl.applibrary.db.bean.ImageBean;

/**
 * Created by xujl on 2017/7/10.
 */

public class ImagePassBean implements Parcelable{
    private Long id;
    /**
     * 数据创建日期
     */
    private long creatDate;
    /**
     * 图片类型：1已收藏，2已下载
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
    public ImagePassBean (ImageBean bean) {
        this.id = bean.getId();
        this.creatDate = bean.getCreatDate();
        this.type = bean.getType();
        this.imageId = bean.getImageId();
        this.imageUrl = bean.getImageUrl();
        this.imagePath = bean.getImagePath();

    }
    public ImagePassBean (HomeImagelistPayload.Images bean) {
        this.imageId = bean._id;
        this.imageUrl = bean.url;

    }
    @Override
    public int describeContents () {
        return 0;
    }

    @Override
    public void writeToParcel (Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeLong(this.creatDate);
        dest.writeInt(this.type);
        dest.writeString(this.imageId);
        dest.writeString(this.imageUrl);
        dest.writeString(this.imagePath);
    }

    public ImagePassBean () {
    }

    protected ImagePassBean (Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.creatDate = in.readLong();
        this.type = in.readInt();
        this.imageId = in.readString();
        this.imageUrl = in.readString();
        this.imagePath = in.readString();
    }

    public static final Creator<ImagePassBean> CREATOR = new Creator<ImagePassBean>() {
        @Override
        public ImagePassBean createFromParcel (Parcel source) {
            return new ImagePassBean(source);
        }

        @Override
        public ImagePassBean[] newArray (int size) {
            return new ImagePassBean[size];
        }
    };

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public long getCreatDate () {
        return creatDate;
    }

    public void setCreatDate (long creatDate) {
        this.creatDate = creatDate;
    }

    public int getType () {
        return type;
    }

    public void setType (int type) {
        this.type = type;
    }

    public String getImageId () {
        return imageId;
    }

    public void setImageId (String imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl () {
        return imageUrl;
    }

    public void setImageUrl (String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImagePath () {
        return imagePath;
    }

    public void setImagePath (String imagePath) {
        this.imagePath = imagePath;
    }
}
