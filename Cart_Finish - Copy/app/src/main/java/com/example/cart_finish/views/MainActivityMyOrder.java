package com.example.cart_finish.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cart_finish.R;

public class MainActivityMyOrder extends AppCompatActivity {
        private ImageView btnback1;
        private ImageView btnshopnow1;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_my_order);

            btnback1 = (ImageView) findViewById(R.id.btnback1);
            btnshopnow1 = (ImageView) findViewById(R.id.btnshopnow1);

            btnback1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent btnback1 = new Intent(getApplicationContext(), MainActivityProfile.class);
                    startActivity(btnback1);
                }
            });

            btnshopnow1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent btnshopnow1 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(btnshopnow1);
                }
            });
        }
    }