package com.example.cart_finish.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cart_finish.R;

public class MainActivityWhislist extends AppCompatActivity {
        private ImageView btnback2;
        private ImageView btnshopnow2;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_whislist);
            btnback2 = findViewById(R.id.btnback2);
            btnshopnow2 = findViewById(R.id.btnshopnow2);

            btnback2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent btnback1 = new Intent(getApplicationContext(), MainActivityProfile.class);
                    startActivity(btnback1);
                }
            });

            btnshopnow2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent btnshopnow2 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(btnshopnow2);
                }
            });
        }
    }