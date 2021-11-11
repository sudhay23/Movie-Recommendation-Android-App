package com.example.movierecommendationapp.favorites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
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
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
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
    ArrayList<String> allFavMovieIds;
    private RequestQueue mQueue;
    private ProgressBar progressBar;
    SharedPreferences prefs;
    JSONObject postMovieIds;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        InitializeCardView();

    }
    FavoritesDetails deletedMovie= null;
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();

            deletedMovie=moviesArrayList.get(position);

            moviesArrayList.remove(position);
            adapter.notifyItemRemoved(position);

            Snackbar.make(recyclerView,deletedMovie.getMovieName()+" removed", BaseTransientBottomBar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    moviesArrayList.add(position,deletedMovie);
                    adapter.notifyDataSetChanged();
                }
            }).show();

        }
    };

    private void InitializeCardView() {
        allFavMovieIds = new ArrayList<>();

        prefs = this.getSharedPreferences("LoggedIn", Context.MODE_PRIVATE);
        loggedInEmail = prefs.getString("loggedin","NoEmailLoggedIn");
        recyclerView=findViewById(R.id.favoriterecyclerViewCard);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        moviesArrayList=new ArrayList<>();
        progressBar=(ProgressBar)findViewById(R.id.progress_bar) ;
        mQueue = Volley.newRequestQueue(FavoritesActivity.this);

        progressBarVisible();

        fetchResponseFromDatabaseAndAPI();

        adapter=new FavoritesAdapter(FavoritesActivity.this,moviesArrayList);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);



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
                if(allFavMovieIds.size()>0){
                //Send POST request to backend
                String URL = "https://movie-recommender-fastapi.herokuapp.com/favorites/";
                // String URL="http://127.0.0.1:8000/favorites";
                System.out.println(postMovieIds);
                 jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, postMovieIds, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
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
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                mQueue.add(jsonObjectRequest);}
                else{
                progressBarInVisible();

                }

            }
        });
    }

    private void CreateDataForCards(String movieId,String movieName, String ratings, String movieDescription, String backDropPath, String posterPath) {
        FavoritesDetails movie= new FavoritesDetails(movieId,movieName,ratings,movieDescription,backDropPath,posterPath);
        System.out.println(movieId+" "+movieName+" "+ratings+" "+movieDescription+" "+backDropPath+" "+posterPath);
        moviesArrayList.add(movie);

    }



}