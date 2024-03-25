package com.example.cart_finish.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cart_finish.repository.CartRepo;
import com.example.cart_finish.utils.model.ProdukCart;

import java.util.List;

public class CartViewModel extends AndroidViewModel {

    private CartRepo cartRepo;


    public CartViewModel(@NonNull Application application) {
        super(application);
        cartRepo = new CartRepo(application);
    }

    public LiveData<List<ProdukCart>> getAllCartItems(){
        return cartRepo.getAllCartItemsLiveData();
    }

    public void insertCartItem(ProdukCart produkCart){
        cartRepo.insertCartItem(produkCart);
    }

    public void updateQuantity(int id, int quantity){
        cartRepo.updateQuantity(id, quantity);
    }

    public void updatePrice(int id, double price){
        cartRepo.updatePrice(id, price);
    }

    public void deleteCartItem(ProdukCart produkCart){
        cartRepo.deleteCartItem(produkCart);
    }

    public void deleteAllCartItems(){
        cartRepo.deleteAllCartItems();
    }
}
