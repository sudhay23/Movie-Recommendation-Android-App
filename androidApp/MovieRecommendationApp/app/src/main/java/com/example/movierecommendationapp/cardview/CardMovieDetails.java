package com.example.movierecommendationapp.cardview;

import android.view.View;

public class CardMovieDetails {
    private String movieName;
    private String ratings;
    private String movieDescription;

    public CardMovieDetails(String movieName, String ratings, String movieDescription) {
        this.movieName = movieName;
        this.ratings = ratings;
        this.movieDescription = movieDescription;
    }



    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }
}
