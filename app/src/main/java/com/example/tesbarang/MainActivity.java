package com.example.tesbarang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onClick(View v) {
        startActivity(LihatBarang.getActIntent(MainActivity.this));
    }
});
