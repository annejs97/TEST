package com.example.cart_finish.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cart_finish.R;
import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivityLogin extends AppCompatActivity {

        private EditText editEmail, editPassword;
        private CardView btnLogin, btnGoogle, btnFacebook;
        private TextView btnRegister;
        private ProgressDialog progressDialog;
        private FirebaseAuth mAuth;
        private GoogleSignInClient mGoogleSignInClient;
        private CallbackManager callbackManager;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_login);

            editEmail = findViewById(R.id.email);
            editPassword = findViewById(R.id.password);
            btnLogin = findViewById(R.id.ButtonLogin);
            btnRegister = findViewById(R.id.bagsignUp);
            btnGoogle = findViewById(R.id.google_btn);
            //btnFacebook = findViewById(R.id.facebook_btn);

            mAuth = FirebaseAuth.getInstance();
            progressDialog = new ProgressDialog(MainActivityLogin.this);
            progressDialog.setTitle("Loading");
            progressDialog.setMessage("Silahkan tunggu");
            progressDialog.setCancelable(false);

            //GOOGLE LOGIN
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken("223658491097-861ltl41ea3dqg5va9pff96r2406gp68.apps.googleusercontent.com")
                    .requestEmail()
                    .build();

            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

            btnRegister.setOnClickListener(v -> {
                startActivity(new Intent(getApplicationContext(), MainActivitySignUp.class));
            });

            btnLogin.setOnClickListener(v -> {
                if (editEmail.getText().length()>0 && editPassword.getText().length()>0){
                    login(editEmail.getText().toString(), editPassword.getText().toString());
                }else{
                    Toast.makeText(getApplicationContext(), "Silahkan isi semua data", Toast.LENGTH_SHORT).show();
                }
            });
            btnGoogle.setOnClickListener(v -> {
                googlesignIn();
            });

            //FACEBOOK LOGIN
            //callbackManager = CallbackManager.Factory.create();
            //FacebookSdk.sdkInitialize(getApplicationContext());
            //AppEventsLogger.activateApp(this);



        }

        //GOOGLE LOGIN
        private void googlesignIn() {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent,1000);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 1000){
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    Log.d("GOOGLE SIGN IN", "firebaseAuthWithGoogle:" + account.getId());
                    firebaseAuthWithGoogle(account.getIdToken());
                } catch (ApiException e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        }

        private void firebaseAuthWithGoogle(String idToken){
            AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("GOOGLE SIGN IN", "signInWithCredential:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                reload();
                            }else {
                                Log.w("GOOGLE SIGN IN", task.getException());
                                reload();
                            }
                        }
                    });
        }

        void navigateToSecondActivity(){
            finish();
            Intent intent = new Intent(MainActivityLogin.this, MainActivity.class);
            startActivity(intent);
        }

        private void login(String email, String password){
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful() && task.getResult()!=null){
                        if (task.getResult().getUser()!=null){
                            reload();
                        }else{
                            Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                }
            });
        }

        private void reload(){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
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