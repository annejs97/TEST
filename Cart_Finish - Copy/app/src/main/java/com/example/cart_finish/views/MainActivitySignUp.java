package com.example.cart_finish.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cart_finish.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivitySignUp extends AppCompatActivity {

        private EditText editUsername, editEmail, editPassword, editPasswordConf;
        private ImageButton btnRegister;
        private TextView btnLogin;
        private ProgressDialog progressDialog;
        private FirebaseAuth mAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_sign_up);
            //editFirstname = findViewById(R.id.nama_depan);
            //editLastname = findViewById(R.id.nama_belakang);
            editUsername = findViewById(R.id.username);
            editEmail = findViewById(R.id.email);
            editPassword = findViewById(R.id.password);
            editPasswordConf = findViewById(R.id.password_conf);
            //editPhone = findViewById(R.id.nomor_hp);
            btnRegister = findViewById(R.id.rectangle_3);
            btnLogin = findViewById(R.id.textLogin);

            mAuth = FirebaseAuth.getInstance();
            progressDialog = new ProgressDialog(MainActivitySignUp.this);
            progressDialog.setTitle("Loading");
            progressDialog.setMessage("Silahkan tunggu");
            progressDialog.setCancelable(false);

            btnLogin.setOnClickListener(v ->  {
                startActivity(new Intent(getApplicationContext(), MainActivityLogin.class));
                finish();
            });

            btnRegister.setOnClickListener(v -> {
                if (editUsername.getText().length()>0 && editEmail.getText().length()>0 &&
                        editPassword.getText().length()>0 && editPasswordConf.getText().length()>0){
                    if (editPassword.getText().toString().equals(editPasswordConf.getText().toString())){
                        register(editUsername.getText().toString(), editEmail.getText().toString(),
                                editPassword.getText().toString());
                    }else {
                        Toast.makeText(getApplicationContext(), "Silahkan masukan password yang sama!",
                                Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Silahkan isi semua data", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void register(String username, String email, String password){
            progressDialog.show();
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful() && task.getResult()!=null){
                        FirebaseUser firebaseUser = task.getResult().getUser();
                        if (firebaseUser!=null){
                            UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(username)
                                    .build();
                            firebaseUser.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    reload();
                                }
                            });
                        }else {
                            Toast.makeText(getApplicationContext(), "Register Gagal", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                }
            });
        }



        private void reload(){
            startActivity(new Intent(getApplicationContext(), CartActivity.class));
        }

        @Override
        public void onStart() {
            super.onStart();
            // Check if user is signed in (non-null) and update UI accordingly.
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if(currentUser != null){
                reload();
            }
        }


    }