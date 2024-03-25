package com.example.cart_finish.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.cart_finish.utils.model.ProdukCart;

import java.util.List;

@Dao
public interface CartDAO {

    @Insert
    void insertCartItem(ProdukCart produkCart);

    @Query("SELECT * FROM produk_table")
    LiveData<List<ProdukCart>> getAllCartItems();

    @Delete
    void deleteCartItem(ProdukCart produkCart);

    @Query("UPDATE produk_table SET quantity=:quantity WHERE id=:id")
    void updateQuantity(int id, int quantity);

    @Query("UPDATE produk_table SET totalItemPrice=:totalItemPrice WHERE id=:id")
    void updatePrice(int id, double totalItemPrice);

    @Query("DELETE FROM produk_table")
    void deleteAllItems();

}
