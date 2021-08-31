package com.example.movierecommendationapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class movieslist extends Activity {
    ListView listView;
    Button b1;
    int i=0;
    String moviename[] = {
            "Johnny English Strikes\nAgain",
            "The Tomorrow War",
            "The Dead Don't Die",
            "Forensic",
            "Tamasha",
            "Zodiac"
    };
    String[] selectedmovies;
    ArrayList<String> mylist = new ArrayList<String>();

    String movietype[] = {
            "comedy",
            "action",
            "horror",
            "thriller",
            "romance",
            "mystery"
    };


    Integer imageid[] = {
            R.drawable.comedy,
            R.drawable.action,
            R.drawable.horror,
            R.drawable.thriller,
            R.drawable.romance,
            R.drawable.mystery

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movieslist);
        // Setting header
        TextView textView = new TextView(this);
        textView.setTypeface(Typeface.DEFAULT_BOLD);


        ListView listView=(ListView)findViewById(android.R.id.list);
        b1=findViewById(R.id.button);
        listView.addHeaderView(textView);

        // For populating list data
        custommovielist customCountryList = new custommovielist(this, moviename, movietype, imageid);
        listView.setAdapter(customCountryList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                listView.setItemChecked(position, true);
                Toast.makeText(getApplicationContext(),"You Selected "+moviename[position-1],Toast.LENGTH_SHORT).show();
                mylist.add(movietype[position-1]);


            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), recommendedmovies.class);
                intent.putExtra("mylist", mylist);
                startActivity(intent);
            }
        });
    }
}
