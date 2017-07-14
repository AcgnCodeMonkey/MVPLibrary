package com.xujl.mvpllirary.json;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by xujl on 2017/7/6.
 */

public class HomePayload {
    public String col;
    public String tag;
    public String tag3;
    public String sort;
    public String totalNum;
    public String startIndex;
    public String returnNumber;
    public List<Images> imgs;

    public static class Images implements Parcelable{
        public String imageUrl;
        public String id;
        public String title;

        @Override
        public int describeContents () {
            return 0;
        }

        @Override
        public void writeToParcel (Parcel dest, int flags) {
            dest.writeString(this.imageUrl);
            dest.writeString(this.id);
            dest.writeString(this.title);
        }

        public Images () {
        }

        protected Images (Parcel in) {
            this.imageUrl = in.readString();
            this.id = in.readString();
            this.title = in.readString();
        }

        public static final Creator<Images> CREATOR = new Creator<Images>() {
            @Override
            public Images createFromParcel (Parcel source) {
                return new Images(source);
            }

            @Override
            public Images[] newArray (int size) {
                return new Images[size];
            }
        };
    }
}
