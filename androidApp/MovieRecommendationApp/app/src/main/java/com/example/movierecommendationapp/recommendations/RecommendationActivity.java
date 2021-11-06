package com.example.movierecommendationapp.recommendations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
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
import com.example.movierecommendationapp.cardview.CardMovieDetails;
import com.example.movierecommendationapp.cardview.CardViewAdapter;
import com.example.movierecommendationapp.favorites.FavoritesActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecommendationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardViewAdapter adapter;
    private ArrayList<CardMovieDetails> moviesArrayList;

    private RequestQueue mQueue;
    private ProgressBar progressBar;
    private Intent intent;
    private String movie_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);

        InitializeCardView();
    }

    private void InitializeCardView() {
        recyclerView=findViewById(R.id.recommendationsRecyclerViewCard);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        moviesArrayList=new ArrayList<>();
        progressBar=(ProgressBar)findViewById(R.id.progress_bar) ;
        intent= getIntent() ;
        movie_id = intent.getStringExtra("movie_id");

        mQueue= Volley.newRequestQueue(this);
        progressBarVisible();
        jsonParse();

        adapter=new CardViewAdapter(this,moviesArrayList);
        recyclerView.setAdapter(adapter);


    }
    public void progressBarVisible(){
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    public void progressBarInVisible(){
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }
    private void jsonParse() {
        String URL="https://movie-recommender-fastapi.herokuapp.com/movie_id/"+movie_id;
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray keys = response.names ();
                progressBarInVisible();
                for (int i = 0; i < keys.length(); i++) {
                    try {
                        String movieId=(String)keys.get(i);
                        String movie_name=(response.getJSONObject((String) keys.get(i)).getString("original_title"));
                        String description=(response.getJSONObject((String) keys.get(i)).getString("overview"));
                        String ratings="Ratings : "+(response.getJSONObject((String) keys.get(i)).getString("vote_average"))+"/10";
                        String backDropPath=(response.getJSONObject((String) keys.get(i)).getString("backdrop_path"));
                        String posterPath=(response.getJSONObject((String) keys.get(i)).getString("poster_path"));

                        if(description.length()>0){
                            CreateDataForCards(movieId,movie_name,description,ratings,backDropPath,posterPath);
                            adapter.notifyDataSetChanged();
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                //  Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    private void CreateDataForCards(String movieId,String movieName, String ratings, String movieDescription, String backDropPath, String posterPath) {
        CardMovieDetails movie= new CardMovieDetails(movieId,movieName,ratings,movieDescription,backDropPath,posterPath);
        System.out.println(movieId+" "+movieName+" "+ratings+" "+movieDescription+" "+backDropPath+" "+posterPath);
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
                SharedPreferences prefs = this.getSharedPreferences("LoggedIn",MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.commit();
                prefs = this.getSharedPreferences("Register",MODE_PRIVATE);
                editor = prefs.edit();
                editor.clear();
                editor.commit();

                Intent logoutIntent = new Intent(this, MainActivity.class);
                logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                logoutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(logoutIntent);
                return true;
            case R.id.favoritesItem:
                Intent favIntent = new Intent(this, FavoritesActivity.class);
                startActivity(favIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}