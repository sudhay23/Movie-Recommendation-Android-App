package com.example.movierecommendationapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class custommovielist extends ArrayAdapter {
    private String[] moviename;
    private String[] movietype;
    private Integer[] imageid;
    private Activity context;

    public custommovielist(Activity context, String[] moviename, String[] movietype, Integer[] imageid) {
        super(context, R.layout.row_item, moviename);
        this.context = context;
        this.moviename = moviename;
        this.movietype = movietype;
        this.imageid = imageid;

    }

    public custommovielist(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.row_item, null, true);
        TextView textViewCountry = (TextView) row.findViewById(R.id.textViewCountry);
        TextView textViewCapital = (TextView) row.findViewById(R.id.textViewCapital);
        ImageView imageFlag = (ImageView) row.findViewById(R.id.imageViewFlag);

        textViewCountry.setText(moviename[position]);
        textViewCapital.setText(movietype[position]);
        imageFlag.setImageResource(imageid[position]);
        return  row;
    }
}
