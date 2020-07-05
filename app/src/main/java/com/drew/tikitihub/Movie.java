package com.drew.tikitihub;

public class Movie {
    //declare member variables
    private final int movie_poster;
    private String movie_title;

    // Create constructor and pass above parameters

    public Movie(int movie_poster, String movie_title) {
        this.movie_poster = movie_poster;
        this.movie_title = movie_title;
    }

    public int getMovie_poster() {
        return movie_poster;
    }

    public String getMovie_title() {
        return movie_title;
    }

}
