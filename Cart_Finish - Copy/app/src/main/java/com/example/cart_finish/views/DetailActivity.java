package com.example.cart_finish.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cart_finish.R;
import com.example.cart_finish.utils.model.ProdukCart;
import com.example.cart_finish.utils.model.ProdukItem;
import com.example.cart_finish.viewmodel.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private ImageView produkImageView;
    private TextView produkNameTv, produkBrandNameTv, produkPriceTv;
    private AppCompatButton addToCartBtn;
    private ProdukItem produk;
    private CartViewModel viewModel;
    private List<ProdukCart> produkCartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        produk = getIntent().getParcelableExtra("produkItem");
        initializeVariables();

        viewModel.getAllCartItems().observe(this, new Observer<List<ProdukCart>>() {
            @Override
            public void onChanged(List<ProdukCart> produkCarts) {
                produkCartList.addAll(produkCarts);
            }
        });

        if (produk !=null){
            setDataToWidgets();
        }

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertToRoom();
            }
        });

    }

    private void insertToRoom(){
        ProdukCart produkCart = new ProdukCart();
        produkCart.setProdukName(produk.getProdukName());
        produkCart.setProdukBrandName(produk.getProdukBrandName());
        produkCart.setProdukPrice(produk.getProdukPrice());
        produkCart.setProdukImage(produk.getProdukImage());

        final int[] quantity = {1};
        final int[] id = new int[1];

        if (!produkCartList.isEmpty()){

            for (int i=0;i<produkCartList.size();i++){
                if (produkCart.getProdukName().equals(produkCartList.get(i).getProdukName())){
                    quantity[0] = produkCartList.get(i).getQuantity();
                    quantity[0]++;
                    id[0] = produkCartList.get(i).getId();
                }
            }
        }

        if (quantity[0]==1){
            produkCart.setQuantity(quantity[0]);
            produkCart.setTotalItemPrice(quantity[0]*produkCart.getProdukPrice());
            viewModel.insertCartItem(produkCart);
        }else{
            viewModel.updateQuantity(id[0], quantity[0]);
            viewModel.updatePrice(id[0], quantity[0]*produkCart.getProdukPrice());
        }

        startActivity(new Intent(DetailActivity.this , CartActivity.class));
    }

    private void setDataToWidgets(){
        produkNameTv.setText(produk.getProdukName());
        produkBrandNameTv.setText(produk.getProdukBrandName());
        produkPriceTv.setText(String.valueOf(produk.getProdukPrice()));
        produkImageView.setImageResource(produk.getProdukImage());

    }

    private void initializeVariables(){

        produkCartList = new ArrayList<>();
        produkImageView = findViewById(R.id.detailActivityProdukIV);
        produkNameTv = findViewById(R.id.detailActivityProdukNameTv);
        produkBrandNameTv = findViewById(R.id.detailActivityProdukBrandNameTv);
        produkPriceTv = findViewById(R.id.detailActivityProdukPriceTv);
        addToCartBtn = findViewById(R.id.detailActivityAddToCartBtn);

        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
    }
}