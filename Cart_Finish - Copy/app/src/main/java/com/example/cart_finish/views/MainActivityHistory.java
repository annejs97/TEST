package com.example.cart_finish.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cart_finish.R;

public class MainActivityHistory extends AppCompatActivity {
    private ImageView btnback3;
    private ImageView btnshopnow3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_history);

        btnback3 = (ImageView) findViewById(R.id.btnback3);
        btnshopnow3 = (ImageView) findViewById(R.id.btnshopnow3);

        btnback3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnback3 = new Intent(getApplicationContext(), MainActivityProfile.class);
                startActivity(btnback3);
            }
        });

        btnshopnow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnshopnow3 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(btnshopnow3);
            }
        });
    }

}