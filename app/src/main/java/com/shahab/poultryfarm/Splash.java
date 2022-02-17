package com.shahab.poultryfarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {
    private static final int SPLASH = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Splash.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(0,0);
            finish();
        },SPLASH);

    }
}