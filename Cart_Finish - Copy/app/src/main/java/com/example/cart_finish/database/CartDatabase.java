package com.example.cart_finish.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cart_finish.dao.CartDAO;
import com.example.cart_finish.utils.model.ProdukCart;


@Database(entities = {ProdukCart.class}, version = 1)
public abstract class CartDatabase extends RoomDatabase {

    public abstract CartDAO cartDAO();
    private static CartDatabase instance;

    public static synchronized CartDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext()
                            , CartDatabase.class, "ProdukDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
