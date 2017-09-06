package com.xujl.mvpllirary.json;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by xujl on 2017/7/6.
 */

public class HomeImagelistPayload {
    public String error;
    public List<Images> results;

    public static class Images implements Parcelable{
        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public String used;
        public String who;

        @Override
        public int describeContents () {
            return 0;
        }

        @Override
        public void writeToParcel (Parcel dest, int flags) {
            dest.writeString(this._id);
            dest.writeString(this.createdAt);
            dest.writeString(this.desc);
            dest.writeString(this.publishedAt);
            dest.writeString(this.source);
            dest.writeString(this.type);
            dest.writeString(this.url);
            dest.writeString(this.used);
            dest.writeString(this.who);
        }

        public Images () {
        }

        protected Images (Parcel in) {
            this._id = in.readString();
            this.createdAt = in.readString();
            this.desc = in.readString();
            this.publishedAt = in.readString();
            this.source = in.readString();
            this.type = in.readString();
            this.url = in.readString();
            this.used = in.readString();
            this.who = in.readString();
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
