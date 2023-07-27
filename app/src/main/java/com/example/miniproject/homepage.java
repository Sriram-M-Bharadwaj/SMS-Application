package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    startActivity(new Intent(homepage.this, MainActivity.class));

                finish();}
        },2000);

    }
}