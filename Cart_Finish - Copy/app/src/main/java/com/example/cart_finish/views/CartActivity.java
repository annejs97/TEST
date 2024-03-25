package com.example.cart_finish.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cart_finish.R;
import com.example.cart_finish.utils.adapter.CartAdapter;
import com.example.cart_finish.utils.model.ProdukCart;
import com.example.cart_finish.viewmodel.CartViewModel;

import java.util.List;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartClickedListeners {

    private RecyclerView recyclerView;
    private CartViewModel cartViewModel;
    private TextView totalCartPriceTv, textView;
    private AppCompatButton checkoutBtn;
    private CardView cardView;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initializeVariables();

        cartViewModel.getAllCartItems().observe(this, new Observer<List<ProdukCart>>() {
            @Override
            public void onChanged(List<ProdukCart> produkCarts) {
                double price = 0;
                cartAdapter.setProdukCartList(produkCarts);
                for (int i=0;i<produkCarts.size();i++){
                    price = price + produkCarts.get(i).getTotalItemPrice();

                }

                totalCartPriceTv.setText(String.valueOf(price));
            }
        });

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartViewModel.deleteAllCartItems();
                textView.setVisibility(View.VISIBLE);
                checkoutBtn.setVisibility(View.VISIBLE);
                totalCartPriceTv.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initializeVariables(){

        cartAdapter = new CartAdapter(this);
        textView = findViewById(R.id.textView2);
        cardView = findViewById(R.id.cartActivityCardView);
        totalCartPriceTv = findViewById(R.id.cartActivityTotalPriceTv);
        checkoutBtn = findViewById(R.id.cartActivityCheckoutBtn);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        recyclerView = findViewById(R.id.cartRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(cartAdapter);
    }

    @Override
    public void onDeleteClicked(ProdukCart produkCart) {
        cartViewModel.deleteCartItem(produkCart);

    }

    @Override
    public void onPlusClicked(ProdukCart produkCart) {
        int quantity = produkCart.getQuantity() + 1;
        cartViewModel.updateQuantity(produkCart.getId(), quantity);
        cartViewModel.updatePrice(produkCart.getId(), quantity*produkCart.getProdukPrice());
        cartAdapter.notifyDataSetChanged();

    }

    @Override
    public void onMinusClicked(ProdukCart produkCart) {
        int quantity = produkCart.getQuantity() - 1;
        if (quantity != 0){
            cartViewModel.updateQuantity(produkCart.getId(), quantity);
            cartViewModel.updatePrice(produkCart.getId(), quantity*produkCart.getProdukPrice());
            cartAdapter.notifyDataSetChanged();
        }else {
            cartViewModel.deleteCartItem(produkCart);
        }
    }
}