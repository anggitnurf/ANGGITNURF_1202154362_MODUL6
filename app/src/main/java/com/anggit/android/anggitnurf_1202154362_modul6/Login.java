package com.anggit.android.anggitnurf_1202154362_modul6;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
    //deklarasi
    FirebaseAuth mAuth;
    EditText email, password;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //deklarasi
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        progressBar = (ProgressBar)findViewById(R.id.progmasuk);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.masuk).setOnClickListener(this);
    }
    //memulai intent ke sign up
    public void daftar(View view) {
        //intent ke sign up class
        Intent daftar = new Intent(Login.this, Signup.class);
        //start intent
        startActivity(daftar);
    }
    //kodingan jika masuk
    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.masuk:
                 userLogin();
                 break;
         }
    }

    private void userLogin() {
        String mail = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        //jika email kosong
        if(mail.isEmpty()){
            email.setError("masukkan email dulu");
            email.requestFocus();
            return;
        }
        //jika password kosong
        if(pass.isEmpty()){
            password.setError("masukkan password dulu");
            password.requestFocus();
            return;
        }
        //set progresbar hilang
        progressBar.setVisibility(View.VISIBLE);
        //toast untuk sign in
        mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Intent intent = new Intent(Login.this, Home.class);
                    Toast.makeText(getApplicationContext(),"anda berhasil login",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"anda belum daftar",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
