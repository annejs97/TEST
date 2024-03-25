package com.example.cart_finish.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.cart_finish.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MainActivityScanner extends AppCompatActivity {

        private ImageView ivBgContent;
        private CodeScanner mCodeScanner;
        private CodeScannerView scannerView;
        BottomNavigationView bottomNavigationView;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_splash_screen);

            ivBgContent = findViewById(R.id.ivBgContent);
            scannerView = findViewById(R.id.scannerView);
            bottomNavigationView = findViewById(R.id.navigation_bottom);

            bottomNavigationView.setSelectedItemId(R.id.scannermenu);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId())
                    {
                        case R.id.cartmenu:
                            startActivity(new Intent(getApplicationContext(),CartActivity.class));
                            overridePendingTransition(0,0);
                            return true;

                        case R.id.reviewmenu:
                            startActivity(new Intent(getApplicationContext(),MainActivityReview.class));
                            overridePendingTransition(0,0);
                            return true;

                        case R.id.scannermenu:
                            return true;

                        case R.id.profilemenu:
                            startActivity(new Intent(getApplicationContext(),MainActivityProfile.class));
                            overridePendingTransition(0,0);
                            return true;
                    }

                    return false;
                }
            });
            mCodeScanner = new CodeScanner(this, scannerView);
            mCodeScanner.setDecodeCallback(new DecodeCallback() {
                @Override
                public void onDecoded(@NonNull Result result) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String message = "result :\n" + result.getText();
                            showAlertDialog(message);
                        }
                    });
                }
            });
            checkCameraPermission();
        }

        @Override
        protected void onResume(){
            super.onResume();
            checkCameraPermission();
        }

        @Override
        protected void onPause(){
            mCodeScanner.releaseResources();
            super.onPause();
        }

        private void checkCameraPermission(){
            Dexter.withActivity(this)
                    .withPermission(Manifest.permission.CAMERA)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            mCodeScanner.startPreview();
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {

                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    })
                    .check();
        }

        private void showAlertDialog(String message){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(message);
            builder.setCancelable(true);

            builder.setPositiveButton(
                    "SCAN LAGI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            mCodeScanner.startPreview();
                        }
                    });
            builder.setNegativeButton("CANCEL",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }