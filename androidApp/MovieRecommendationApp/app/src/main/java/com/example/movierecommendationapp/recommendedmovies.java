package com.example.movierecommendationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class recommendedmovies extends AppCompatActivity {
    TextView tv1, tv2, tv3, tv4, tv5, tv6;
    ImageView iv1, iv2, iv3, iv4, iv5, iv6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendedmovies);
        tv1 = findViewById(R.id.textview1);
        tv2 = findViewById(R.id.textview2);
        tv3 = findViewById(R.id.textview3);
        tv4 = findViewById(R.id.textview4);
        tv5 = findViewById(R.id.textview5);
        tv6 = findViewById(R.id.textview6);

        iv1 = findViewById(R.id.image1);
        iv2 = findViewById(R.id.image2);
        iv3 = findViewById(R.id.image3);
        iv4 = findViewById(R.id.image4);
        iv5 = findViewById(R.id.image5);
        iv6 = findViewById(R.id.image6);

        Bundle bundle = getIntent().getExtras();
        ArrayList<String> mylist = (ArrayList<String>) getIntent().getSerializableExtra("mylist");
        int s= mylist.size();
        for(int i=0;i<s;i++)
        {
            if(i==0)
            {
                if(mylist.get(i).equals("comedy"))
                {
                    tv1.setText("Hang Over\n\nComedy\n\n9.1/10");
                    iv1.setImageResource(R.drawable.hangover);
                }
                if(mylist.get(i).equals("thriller"))
                {
                    tv1.setText("Curse of the Nun\n\nThriller\n\n8.5/10");
                    iv1.setImageResource(R.drawable.curseofthenun);
                }
                if(mylist.get(i).equals("action"))
                {
                    tv1.setText("Skylines\n\nAction\n\n7.9/10");
                    iv1.setImageResource(R.drawable.skylines);
                }
                if(mylist.get(i).equals("horror"))
                {
                    tv1.setText("Conjuring 3\n\nHorror\n\n8.1/10");
                    iv1.setImageResource(R.drawable.conjuring3);
                }
                if(mylist.get(i).equals("romance"))
                {
                    tv1.setText("Titanic\n\nRomance\n\n9.5/10");
                    iv1.setImageResource(R.drawable.titanic);
                }
                if(mylist.get(i).equals("mysery"))
                {
                    tv1.setText("Zodiac\n\nMysery\n\n6.9/10");
                    iv1.setImageResource(R.drawable.zodiacmysery);
                }

            }
            if(i==1)
            {
                if(mylist.get(i).equals("comedy"))
                {
                    tv2.setText("Hang Over\n\nComedy\n\n9.1/10");
                    iv2.setImageResource(R.drawable.hangover);
                }
                if(mylist.get(i).equals("thriller"))
                {
                    tv2.setText("Curse of the Nun\n\nThriller\n\n8.5/10");
                    iv2.setImageResource(R.drawable.curseofthenun);
                }
                if(mylist.get(i).equals("action"))
                {
                    tv2.setText("Skylines\n\nAction\n\n7.9/10");
                    iv2.setImageResource(R.drawable.skylines);
                }
                if(mylist.get(i).equals("horror"))
                {
                    tv2.setText("Conjuring 3\n\nHorror\n\n8.1/10");
                    iv2.setImageResource(R.drawable.conjuring3);
                }
                if(mylist.get(i).equals("romance"))
                {
                    tv2.setText("Titanic\n\nRomance\n\n9.5/10");
                    iv2.setImageResource(R.drawable.titanic);
                }
                if(mylist.get(i).equals("mysery"))
                {
                    tv2.setText("Zodiac\n\nMysery\n\n6.9/10");
                    iv2.setImageResource(R.drawable.zodiacmysery);
                }

            }
            if(i==2)
            {
                if(mylist.get(i).equals("comedy"))
                {
                    tv3.setText("Hang Over\n\nComedy\n\n9.1/10");
                    iv3.setImageResource(R.drawable.hangover);
                }
                if(mylist.get(i).equals("thriller"))
                {
                    tv3.setText("Curse of the Nun\n\nThriller\n\n8.5/10");
                    iv3.setImageResource(R.drawable.curseofthenun);
                }
                if(mylist.get(i).equals("action"))
                {
                    tv3.setText("Skylines\n\nAction\n\n7.9/10");
                    iv3.setImageResource(R.drawable.skylines);
                }
                if(mylist.get(i).equals("horror"))
                {
                    tv3.setText("Conjuring 3\n\nHorror\n\n8.1/10");
                    iv3.setImageResource(R.drawable.conjuring3);
                }
                if(mylist.get(i).equals("romance"))
                {
                    tv3.setText("Titanic\n\nRomance\n\n9.5/10");
                    iv3.setImageResource(R.drawable.titanic);
                }
                if(mylist.get(i).equals("mysery"))
                {
                    tv3.setText("Zodiac\n\nMysery\n\n6.9/10");
                    iv3.setImageResource(R.drawable.zodiacmysery);
                }

            }
            if(i==3)
            {
                if(mylist.get(i).equals("comedy"))
                {
                    tv4.setText("Hang Over\n\nComedy\n\n9.1/10");
                    iv4.setImageResource(R.drawable.hangover);
                }
                if(mylist.get(i).equals("thriller"))
                {
                    tv4.setText("Curse of the Nun\n\nThriller\n\n8.5/10");
                    iv4.setImageResource(R.drawable.curseofthenun);
                }
                if(mylist.get(i).equals("action"))
                {
                    tv4.setText("Skylines\n\nAction\n\n7.9/10");
                    iv4.setImageResource(R.drawable.skylines);
                }
                if(mylist.get(i).equals("horror"))
                {
                    tv4.setText("Conjuring 3\n\nHorror\n\n8.1/10");
                    iv4.setImageResource(R.drawable.conjuring3);
                }
                if(mylist.get(i).equals("romance"))
                {
                    tv4.setText("Titanic\n\nRomance\n\n9.5/10");
                    iv4.setImageResource(R.drawable.titanic);
                }
                if(mylist.get(i).equals("mysery"))
                {
                    tv4.setText("Zodiac\n\nMysery\n\n6.9/10");
                    iv4.setImageResource(R.drawable.zodiacmysery);
                }

            }
            if(i==4)
            {
                if(mylist.get(i).equals("comedy"))
                {
                    tv5.setText("Hang Over\n\nComedy\n\n9.1/10");
                    iv5.setImageResource(R.drawable.hangover);
                }
                if(mylist.get(i).equals("thriller"))
                {
                    tv5.setText("Curse of the Nun\n\nThriller\n\n8.5/10");
                    iv5.setImageResource(R.drawable.curseofthenun);
                }
                if(mylist.get(i).equals("action"))
                {
                    tv5.setText("Skylines\n\nAction\n\n7.9/10");
                    iv5.setImageResource(R.drawable.skylines);
                }
                if(mylist.get(i).equals("horror"))
                {
                    tv5.setText("Conjuring 3\n\nHorror\n\n8.1/10");
                    iv5.setImageResource(R.drawable.conjuring3);
                }
                if(mylist.get(i).equals("romance"))
                {
                    tv5.setText("Titanic\n\nRomance\n\n9.5/10");
                    iv5.setImageResource(R.drawable.titanic);
                }
                if(mylist.get(i).equals("mysery"))
                {
                    tv5.setText("Zodiac\n\nMysery\n\n6.9/10");
                    iv5.setImageResource(R.drawable.zodiacmysery);
                }

            }
            if(i==5)
            {
                if(mylist.get(i).equals("comedy"))
                {
                    tv6.setText("Hang Over\n\nComedy\n\n9.1/10");
                    iv6.setImageResource(R.drawable.hangover);
                }
                if(mylist.get(i).equals("thriller"))
                {
                    tv6.setText("Curse of the Nun\n\nThriller\n\n8.5/10");
                    iv6.setImageResource(R.drawable.curseofthenun);
                }
                if(mylist.get(i).equals("action"))
                {
                    tv6.setText("Skylines\n\nAction\n\n7.9/10");
                    iv6.setImageResource(R.drawable.skylines);
                }
                if(mylist.get(i).equals("horror"))
                {
                    tv6.setText("Conjuring 3\n\nHorror\n\n8.1/10");
                    iv6.setImageResource(R.drawable.conjuring3);
                }
                if(mylist.get(i).equals("romance"))
                {
                    tv6.setText("Titanic\n\nRomance\n\n9.5/10");
                    iv6.setImageResource(R.drawable.titanic);
                }
                if(mylist.get(i).equals("mysery"))
                {
                    tv6.setText("Zodiac\n\nMysery\n\n6.9/10");
                    iv6.setImageResource(R.drawable.zodiacmysery);
                }

            }
        }
    }
}