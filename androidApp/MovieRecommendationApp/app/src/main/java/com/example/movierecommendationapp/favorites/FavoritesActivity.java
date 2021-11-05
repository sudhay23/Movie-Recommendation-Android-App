package com.example.movierecommendationapp.favorites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movierecommendationapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    String loggedInEmail;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView tempHolder;

    String ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        tempHolder = findViewById(R.id.tempHolder);
        final ArrayList<String>[] arrayList = new ArrayList[]{null};

        ans = "";


        SharedPreferences prefs = this.getSharedPreferences("LoggedIn", Context.MODE_PRIVATE);
        loggedInEmail = prefs.getString("loggedin","NoEmailLoggedIn");

        //Get favorites info from DB
        db.collection("favoriteMovies").whereEqualTo("email",loggedInEmail).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> allSnaps = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot snapshot: allSnaps)
                {
                    ans+=snapshot.getString("favMovieId")+"\n";
                    arrayList= new ArrayList<String>();
                    arrayList[0].add("tag"); // Add into list like this

                }
               tempHolder.setText(ans);
                
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(FavoritesActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                Log.d("Favs",e.toString());
            }
        });
        System.out.println(arrayList);
        JSONObject json= new JSONObject();
      /* JSONArray jsonArray = new JSONArray(arrayList);
        try {
            json.put("favorite",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/


    }
}