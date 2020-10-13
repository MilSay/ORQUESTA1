package com.android.app.orquesta.main;

import android.os.Parcel;
import android.os.Parcelable;

public class CustomProductInventory implements Parcelable {

    int inventory_id;
    int color_id;
    int size_id;
    int product_id;
    int available_qty;
    String color_name;
    String color_code;
    String size_name;
    String store_name;
    String gallery_name;
    String additional;
    String politics;


    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }

    public int getSize_id() {
        return size_id;
    }

    public void setSize_id(int size_id) {
        this.size_id = size_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getAvailable_qty() {
        return available_qty;
    }

    public void setAvailable_qty(int available_qty) {
        this.available_qty = available_qty;
    }

    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }

    public String getColor_code() {
        return color_code;
    }

    public void setColor_code(String color_code) {
        this.color_code = color_code;
    }

    public String getSize_name() {
        return size_name;
    }

    public void setSize_name(String size_name) {
        this.size_name = size_name;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String size_name) {
        this.store_name = store_name;
    }

    public String getGallery_name() {
        return gallery_name;
    }

    public void setGallery_name(String gallery_name) {
        this.gallery_name = gallery_name;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.inventory_id);
        dest.writeInt(this.color_id);
        dest.writeInt(this.size_id);
        dest.writeInt(this.product_id);
        dest.writeInt(this.available_qty);
        dest.writeString(this.color_name);
        dest.writeString(this.color_code);
        dest.writeString(this.size_name);
        dest.writeString(this.store_name);
        dest.writeString(this.gallery_name);
        dest.writeString(this.additional);
        dest.writeString(this.politics);
    }

    public CustomProductInventory() {
    }

    protected CustomProductInventory(Parcel in) {
        this.inventory_id = in.readInt();
        this.color_id = in.readInt();
        this.size_id = in.readInt();
        this.product_id = in.readInt();
        this.available_qty = in.readInt();
        this.color_name = in.readString();
        this.color_code = in.readString();
        this.size_name = in.readString();
        this.store_name = in.readString();
        this.gallery_name = in.readString();
        this.additional = in.readString();
        this.politics = in.readString();
    }

    public static final Parcelable.Creator<CustomProductInventory> CREATOR = new Parcelable.Creator<CustomProductInventory>() {
        @Override
        public CustomProductInventory createFromParcel(Parcel source) {
            return new CustomProductInventory(source);
        }

        @Override
        public CustomProductInventory[] newArray(int size) {
            return new CustomProductInventory[size];
        }
    };
}
