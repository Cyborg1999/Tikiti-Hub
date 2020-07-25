package com.drew.tikitihub.extra;

import android.widget.ImageView;

import com.drew.tikitihub.models.Movie;

public interface MovieItemClickListener {

    void onMovieClick(Movie movie, ImageView moviePoster); // need image view to make share animation in the 2 activities
}
