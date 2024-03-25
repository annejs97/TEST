package com.example.cart_finish.utils.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "produk_table")
public class ProdukCart {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String produkName, produkBrandName;
    private int produkImage;
    private double produkPrice;

    private int quantity;
    private double totalItemPrice;



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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(double totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }
}









