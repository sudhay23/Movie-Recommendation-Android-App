package com.example.movierecommendationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.movierecommendationapp.cardview.CardViewActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    Button startBtn;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = findViewById(R.id.startBtn);
        startBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==startBtn.getId())
        {
            Intent intent = new Intent(MainActivity.this, CardViewActivity.class);
            startActivity(intent);
        }
    }
}