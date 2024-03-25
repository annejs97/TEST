package com.example.cart_finish.utils.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProdukItem implements Parcelable{

    private String produkName, produkBrandName;
    private int produkImage;
    private double produkPrice;

    public ProdukItem(String produkName, String produkBrandName, int produkImage, double produkPrice) {
        this.produkName = produkName;
        this.produkBrandName = produkBrandName;
        this.produkImage = produkImage;
        this.produkPrice = produkPrice;
    }

    protected ProdukItem(Parcel in) {
        produkName = in.readString();
        produkBrandName = in.readString();
        produkImage = in.readInt();
        produkPrice = in.readDouble();
    }

    public static final Creator<ProdukItem> CREATOR = new Creator<ProdukItem>() {
        @Override
        public ProdukItem createFromParcel(Parcel in) {
            return new ProdukItem(in);
        }

        @Override
        public ProdukItem[] newArray(int size) {
            return new ProdukItem[size];
        }
    };

    public String getProdukName() {
        return produkName;
    }

    public void setProdukName(String produkName) {
        this.produkName = produkName;
    }

    public String getProdukBrandName() {
        return produkBrandName;
    }

    public void setProdukBrandName(String produkBrandName) {
        this.produkBrandName = produkBrandName;
    }

    public int getProdukImage() {
        return produkImage;
    }

    public void setProdukImage(int produkImage) {
        this.produkImage = produkImage;
    }

    public double getProdukPrice() {
        return produkPrice;
    }

    public void setProdukPrice(double produkPrice) {
        this.produkPrice = produkPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(produkName);
        parcel.writeString(produkBrandName);
        parcel.writeInt(produkImage);
        parcel.writeDouble(produkPrice);
    }
}
