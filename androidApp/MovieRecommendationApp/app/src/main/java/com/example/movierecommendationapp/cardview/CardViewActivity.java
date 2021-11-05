package com.example.movierecommendationapp.cardview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.movierecommendationapp.MainActivity;
import com.example.movierecommendationapp.ProfileActivity;
import com.example.movierecommendationapp.R;

import org.json.JSONObject;

import java.util.ArrayList;

public class CardViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardViewAdapter adapter;
    private ArrayList<CardMovieDetails> moviesArrayList;

    private RequestQueue mQueue;




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
        mQueue= Volley.newRequestQueue(this);
        jsonParse();
        CreateDataForCards();
    }

    private void jsonParse() {
        String URL="https://movie-recommender-fastapi.herokuapp.com";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    private void CreateDataForCards() {
        CardMovieDetails movie= new CardMovieDetails("Spiderman","10/10","New Marvel Spiderman movie starring Tom Holland");
        moviesArrayList.add(movie);

        movie= new CardMovieDetails("Spiderman","10/10","New Marvel Spiderman movie starring Tom Holland");
        moviesArrayList.add(movie);

        movie= new CardMovieDetails("Spiderman","10/10","New Marvel Spiderman movie starring Tom Holland");
        moviesArrayList.add(movie);
    }

    //Header menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.five_movie_screen_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.myProfileItem:
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.confirmNoItem:
                return true;
            case R.id.confirmYesItem:
                Intent logoutIntent = new Intent(this, MainActivity.class);
                logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                logoutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(logoutIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}