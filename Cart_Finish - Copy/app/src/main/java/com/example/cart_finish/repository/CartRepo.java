package com.example.cart_finish.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.cart_finish.dao.CartDAO;
import com.example.cart_finish.database.CartDatabase;
import com.example.cart_finish.utils.model.ProdukCart;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CartRepo {

    private CartDAO cartDAO;
    private LiveData<List<ProdukCart>> allCartItemsLiveData;
    private Executor executor = Executors.newSingleThreadExecutor();

    public LiveData<List<ProdukCart>> getAllCartItemsLiveData() {
        return allCartItemsLiveData;
    }

    public CartRepo(Application application){
        cartDAO = CartDatabase.getInstance(application).cartDAO();
        allCartItemsLiveData = cartDAO.getAllCartItems();
    }

    public void insertCartItem(ProdukCart produkCart){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.insertCartItem(produkCart);
            }
        });
    }

    public void deleteCartItem(ProdukCart produkCart){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.deleteCartItem(produkCart);
            }
        });
    }

    public void updateQuantity(int id, int quantity){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.updateQuantity(id, quantity);
            }
        });
    }
    public void updatePrice(int id, double price) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.updatePrice(id, price);
            }
        });
    }
    public void deleteAllCartItems(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.deleteAllItems();
            }
        });
    }
}



