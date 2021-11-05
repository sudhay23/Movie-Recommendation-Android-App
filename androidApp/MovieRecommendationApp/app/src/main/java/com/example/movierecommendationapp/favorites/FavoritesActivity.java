package com.example.movierecommendationapp.favorites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavoritesActivity extends AppCompatActivity {

    String loggedInEmail;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView tempHolder;
    ArrayList<String> allFavMovieIds;
    String ans;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        tempHolder = findViewById(R.id.tempHolder);
        ans = "";
        allFavMovieIds = new ArrayList<>();

        SharedPreferences prefs = this.getSharedPreferences("LoggedIn", Context.MODE_PRIVATE);
        loggedInEmail = prefs.getString("loggedin","NoEmailLoggedIn");

        //Get favorites info from DB
        db.collection("favoriteMovies").whereEqualTo("email",loggedInEmail).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                JSONObject postMovieIds = new JSONObject();

                List<DocumentSnapshot> allSnaps = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot snapshot: allSnaps)
                {
                    ans+=snapshot.getString("favMovieId")+"\n";
                    allFavMovieIds.add(snapshot.getString("favMovieId"));
                }
                tempHolder.setText(ans);
                try {
                    postMovieIds.put("movieIds",allFavMovieIds);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                //Send POST request to backend
                mQueue = Volley.newRequestQueue(FavoritesActivity.this);
                 String URL="https://movie-recommender-fastapi.herokuapp.com/favorites/";
               // String URL="http://127.0.0.1:8000/favorites";
                System.out.println(postMovieIds);
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, postMovieIds, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){@Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }};

                mQueue.add(request);
                
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(FavoritesActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                Log.d("Favs",e.toString());
            }
        });
    }
}
