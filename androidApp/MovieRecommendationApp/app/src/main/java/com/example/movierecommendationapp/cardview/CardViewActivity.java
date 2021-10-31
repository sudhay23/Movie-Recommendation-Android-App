package com.example.movierecommendationapp.cardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.movierecommendationapp.R;

import java.util.ArrayList;

public class CardViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardViewAdapter adapter;
    private ArrayList<CardMovieDetails> moviesArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_views);

        InitializeCardView();
    }

    private void InitializeCardView() {
        recyclerView=findViewById(R.id.recyclerViewCard);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        moviesArrayList=new ArrayList<>();

        adapter=new CardViewAdapter(this,moviesArrayList);
        recyclerView.setAdapter(adapter);
        
        CreateDataForCards();
    }

    private void CreateDataForCards() {
        CardMovieDetails movie= new CardMovieDetails("Spiderman","10/10","New Marvel Spiderman movie starring Tom Holland");
        moviesArrayList.add(movie);
    }
}