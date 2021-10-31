package com.example.movierecommendationapp.cardview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movierecommendationapp.R;

import java.util.ArrayList;
import java.util.Locale;


public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.MovieViewHolder> {

    // Card Adapter Class
    private Context context;
    private ArrayList<CardMovieDetails> movies;

    // Constructor


    public CardViewAdapter(Context context, ArrayList<CardMovieDetails> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card,parent,false);
        return  new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        CardMovieDetails movie = movies.get(position);
        holder.setDetails(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{
        private TextView movieName,movieRatings,movieDescription;

        MovieViewHolder(View itemView){
            super(itemView);
            movieName=itemView.findViewById(R.id.movieName);
            movieRatings=itemView.findViewById(R.id.movieRatings);
            movieDescription=itemView.findViewById(R.id.movieDescription);
        }

        void setDetails(CardMovieDetails details){
            movieName.setText(String.format(Locale.US,details.getMovieName()));
            movieRatings.setText(String.format(Locale.US,details.getRatings()));
            movieDescription.setText(String.format(Locale.US,details.getMovieDescription()));
        }
    }

}
