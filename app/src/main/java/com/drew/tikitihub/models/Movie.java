package com.drew.tikitihub.models;

public class Movie {
    private String movie_title, movie_description, movie_genre, movie_cast, movie_trailer, movie_poster, movie_background_cover;

    public Movie(String movie_title, String movie_description, String movie_genre, String movie_cast, String movie_trailer, String movie_poster, String movie_background_cover) {
        this.movie_title = movie_title;
        this.movie_description = movie_description;
        this.movie_genre = movie_genre;
        this.movie_cast = movie_cast;
        this.movie_trailer = movie_trailer;
        this.movie_poster = movie_poster;
        this.movie_background_cover = movie_background_cover;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public String getMovie_description() { return movie_description; }

    public String getMovie_genre() {
        return movie_genre;
    }

    public String getMovie_cast() {
        return movie_cast;
    }

    public String getMovie_poster() {
        return movie_poster;
    }

    public String getMovie_trailer() {
        return movie_trailer;
    }

    public String getMovie_background_cover() {
        return movie_background_cover;
    }

}
