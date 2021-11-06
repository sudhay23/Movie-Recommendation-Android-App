package com.example.movierecommendationapp.recommendations;

public class RecommendationDetails {
    private String movieId;
    private String movieName;
    private String ratings;
    private String movieDescription;
    private String backDropPath;
    private String posterPath;

    public RecommendationDetails(String movieId, String movieName, String ratings, String movieDescription, String backDropPath, String posterPath) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.ratings = ratings;
        this.movieDescription = movieDescription;
        this.backDropPath = backDropPath;
        this.posterPath = posterPath;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
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

    public String getBackDropPath() {
        return backDropPath;
    }

    public void setBackDropPath(String backDropPath) {
        this.backDropPath = backDropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
