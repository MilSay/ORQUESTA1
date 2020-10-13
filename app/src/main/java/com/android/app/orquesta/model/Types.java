package com.android.app.orquesta.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Types implements Parcelable {

    private int id;
    private String title;
    private int sort;
    private String image_name;


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getSort() {
        return sort;
    }

    public String getImage_name() {
        return image_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeInt(this.sort);
        dest.writeString(this.image_name);
    }

    public Types() {
    }

    protected Types(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.sort = in.readInt();
        this.image_name = in.readString();
    }

    public static final Parcelable.Creator<Types> CREATOR = new Parcelable.Creator<Types>() {
        @Override
        public Types createFromParcel(Parcel source) {
            return new Types(source);
        }

        @Override
        public Types[] newArray(int size) {
            return new Types[size];
        }
    };
}
