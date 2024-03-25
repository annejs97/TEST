package com.example.cart_finish.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.cart_finish.R;
import com.example.cart_finish.utils.adapter.ProdukItemAdapter;
import com.example.cart_finish.utils.model.ProdukCart;
import com.example.cart_finish.utils.model.ProdukItem;
import com.example.cart_finish.viewmodel.CartViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ProdukItemAdapter.ProdukClickedListeners {

    private RecyclerView recyclerView;
    private List<ProdukItem> produkItemList;
    private ProdukItemAdapter adapter;
    private CartViewModel viewModel;
    private List<ProdukCart> produkCartList;
    private CoordinatorLayout coordinatorLayout;
    private ImageView cartImageView;
    BottomNavigationView bottomNavigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeVariables();
        setUpList();

        adapter.setProdukItemList(produkItemList);
        recyclerView.setAdapter(adapter);

        bottomNavigationView = findViewById(R.id.navigation_bottom);

        bottomNavigationView.setSelectedItemId(R.id.cartmenu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.cartmenu:
                        return true;

                    case R.id.reviewmenu:
                        startActivity(new Intent(getApplicationContext(),MainActivityReview.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.scannermenu:
                        startActivity(new Intent(getApplicationContext(),MainActivityScanner.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profilemenu:
                        startActivity(new Intent(getApplicationContext(),MainActivityProfile.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });


        cartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        viewModel.getAllCartItems().observe(this, new Observer<List<ProdukCart>>() {
            @Override
            public void onChanged(List<ProdukCart> produkCarts) {
                produkCartList.addAll(produkCarts);
            }
        });
    }

    private void setUpList(){
        produkItemList.add(new ProdukItem("SKINTIFIC Skin Barrier, Repair", "SKINTIFIC", R.drawable.skintific, 139000));
        produkItemList.add(new ProdukItem("Wardah Hydra Rose Dewy Aqua Day Gel", "Wardah", R.drawable.wardah,68000));
        produkItemList.add(new ProdukItem("Simple Hydrating Light Moisturizer", "Simple", R.drawable.simple,78000));
        produkItemList.add(new ProdukItem("Scarlett Whitening Brighlty Ever After Day Cream", "Scarlett", R.drawable.scarlet,56000));
        produkItemList.add(new ProdukItem("Lotions and moisturizers Vaseline", "vaseline", R.drawable.vaseline,65000));
        produkItemList.add(new ProdukItem("Cetaphil Pro AD Skin Restoring 295 mL", "Cetaphil", R.drawable.cetaphil,120000));
        produkItemList.add(new ProdukItem("Azarine Oil Free Brightening Daily", "Azarine", R.drawable.azarine,48000));
        produkItemList.add(new ProdukItem("Ponds Juice Collection Gel moisturizer", "Ponds", R.drawable.pondbs,37000));
        produkItemList.add(new ProdukItem("Cerave Daily Moisturizing Lotions", "Cerafe", R.drawable.cerafe,250000));
        produkItemList.add(new ProdukItem("Originote Hyalucera Moisturizer", "Originote", R.drawable.originote,48000));
        produkItemList.add(new ProdukItem("NPURE Noni Probiotics Moisturizer", "NPURE", R.drawable.npure,80000));
        produkItemList.add(new ProdukItem("Florence by mills Brighten up Brightening toner", "Florence by mills", R.drawable.toner1, 300000));

    }

    private void initializeVariables(){

        cartImageView = findViewById(R.id.cartIv);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        produkCartList = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        produkItemList = new ArrayList<>();
        recyclerView = findViewById(R.id.mainRecylerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new ProdukItemAdapter(this);

    }

    @Override
    public void onCardClicked(ProdukItem produk) {

        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("produkItem", produk);
        startActivity(intent);
    }

    @Override
    public void onAddToCartBtnClicked(ProdukItem produkItem) {
        ProdukCart produkCart = new ProdukCart();
        produkCart.setProdukName(produkItem.getProdukName());
        produkCart.setProdukBrandName(produkItem.getProdukBrandName());
        produkCart.setProdukPrice(produkItem.getProdukPrice());
        produkCart.setProdukImage(produkItem.getProdukImage());

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

        makeSnackBar("Item Added To Cart");
    }

    private void makeSnackBar(String msg){
        Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_SHORT)
                .setAction("Go to Cart", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this , CartActivity.class));
                    }
                }).show();
    }
}