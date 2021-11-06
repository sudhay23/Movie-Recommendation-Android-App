package com.example.movierecommendationapp.favorites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.movierecommendationapp.R;
import com.example.movierecommendationapp.cardview.CardMovieDetails;
import com.example.movierecommendationapp.cardview.CardViewAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavoritesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FavoritesAdapter adapter;
    private ArrayList<FavoritesDetails> moviesArrayList;

    String loggedInEmail;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    // TextView tempHolder;
    ArrayList<String> allFavMovieIds;
    private RequestQueue mQueue;
    private ProgressBar progressBar;
    SharedPreferences prefs;
    JSONObject postMovieIds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        InitializeCardView();

    }
    private void InitializeCardView() {
        allFavMovieIds = new ArrayList<>();

        prefs = this.getSharedPreferences("LoggedIn", Context.MODE_PRIVATE);
        loggedInEmail = prefs.getString("loggedin","NoEmailLoggedIn");
        recyclerView=findViewById(R.id.recyclerViewCard);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        moviesArrayList=new ArrayList<>();
        progressBar=(ProgressBar)findViewById(R.id.progress_bar) ;
        mQueue = Volley.newRequestQueue(FavoritesActivity.this);


        fetchResponseFromDatabaseAndAPI();
        CreateDataForCards("1234","Spiderman","10","very good","link","link");

        adapter=new FavoritesAdapter(FavoritesActivity.this,moviesArrayList);
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

    private void fetchResponseFromDatabaseAndAPI() {
        //Get favorites info from DB
        db.collection("favoriteMovies").whereEqualTo("email",loggedInEmail).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                postMovieIds = new JSONObject();

                List<DocumentSnapshot> allSnaps = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot snapshot : allSnaps) {
                    allFavMovieIds.add(snapshot.getString("favMovieId"));
                }
                //    tempHolder.setText(ans);
                try {
                    JSONArray jsonArray=new JSONArray(allFavMovieIds);
                    postMovieIds.put("movieIds", jsonArray);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Send POST request to backend
                String URL = "https://movie-recommender-fastapi.herokuapp.com/favorites/";
                // String URL="http://127.0.0.1:8000/favorites";
                System.out.println(postMovieIds);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, postMovieIds, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        CreateDataForCards("1234","Spiderman","10","very good","link","link");
                        adapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                mQueue.add(jsonObjectRequest);
            }
        });
    }

    private void CreateDataForCards(String movieId,String movieName, String ratings, String movieDescription, String backDropPath, String posterPath) {
        FavoritesDetails movie= new FavoritesDetails(movieId,movieName,ratings,movieDescription,backDropPath,posterPath);
        System.out.println(movieId+" "+movieName+" "+ratings+" "+movieDescription+" "+backDropPath+" "+posterPath);
        moviesArrayList.add(movie);

    }
}