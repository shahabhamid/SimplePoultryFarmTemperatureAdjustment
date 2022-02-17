package com.shahab.poultryfarm;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.shahab.poultryfarm.Fan.FanActivity;
import com.shahab.poultryfarm.Humidity.HumidityActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.mFan).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FanActivity.class);
            startActivity(intent);
            });
        findViewById(R.id.mHumidity).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HumidityActivity.class);
            startActivity(intent);});
    }
}