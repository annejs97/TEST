package com.example.cart_finish.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cart_finish.R;

public class MainActivityMyInfo extends AppCompatActivity {
        private ImageView btnback4;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_my_info);

            btnback4 = (ImageView) findViewById(R.id.btnback4);

            btnback4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent btnback4 = new Intent(getApplicationContext(), MainActivityProfile.class);
                    startActivity(btnback4);
                }
            });
        }
    }