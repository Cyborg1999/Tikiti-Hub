package com.drew.tikitihub.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.drew.tikitihub.models.Movie;
import com.drew.tikitihub.adapters.MovieAdapter;
import com.drew.tikitihub.extra.MovieItemClickListener;
import com.drew.tikitihub.R;
import com.drew.tikitihub.adapters.SlidePagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity implements MovieItemClickListener {

    private List<Movie> listMovies, listSlides;
    private ViewPager slidePager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        slidePager = findViewById(R.id.slider_pager);
        TabLayout indicator = findViewById(R.id.slide_indicator);

        listSlides = new ArrayList<>();
        listMovies = new ArrayList<>();

        initializeData();
        SlidePagerAdapter adapter = new SlidePagerAdapter(this, listSlides);
        slidePager.setAdapter(adapter);
        //setup time
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new HomeActivity.SlideTimer(), 3000, 5000);
        indicator.setupWithViewPager(slidePager, true);

        // recycler view setup
        RecyclerView nowShowingRecyclerView = findViewById(R.id.now_showing_recycler_view);
        RecyclerView comingSoonRecyclerView = findViewById(R.id.coming_soon_recycler_view);
        MovieAdapter movieAdapter = new MovieAdapter(this, listMovies, this);
        nowShowingRecyclerView.setAdapter(movieAdapter);
        nowShowingRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        nowShowingRecyclerView.setHasFixedSize(true);
        comingSoonRecyclerView.setAdapter(movieAdapter);
        comingSoonRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        comingSoonRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onMovieClick(Movie movie, ImageView moviePoster) {

        Intent viewMovieIntent = new Intent(this, MovieDetailActivity.class);
        viewMovieIntent.putExtra("movieTitle", movie.getMovie_title());
        viewMovieIntent.putExtra("movieDescription", movie.getMovie_description());
        viewMovieIntent.putExtra("movieGenre", movie.getMovie_genre());
        viewMovieIntent.putExtra("movieCast", movie.getMovie_cast());
        viewMovieIntent.putExtra("moviePoster", movie.getMovie_poster());
        viewMovieIntent.putExtra("movieTrailer", movie.getMovie_trailer());
        viewMovieIntent.putExtra("movieBackgroundCover", movie.getMovie_background_cover());

        if(Build.VERSION.SDK_INT >= 21){
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(HomeActivity.this, moviePoster, "sharedName");
            startActivity(viewMovieIntent, options.toBundle());
        }else{
            startActivity(viewMovieIntent);
        }

        Toast.makeText(this, movie.getMovie_title(), Toast.LENGTH_LONG).show();
    }

    class SlideTimer extends TimerTask{

        @Override
        public void run() {
            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(slidePager.getCurrentItem()<listSlides.size()-1){
                      slidePager.setCurrentItem(slidePager.getCurrentItem()+1);
                    }else {
                        slidePager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    private void initializeData() {
        String[] movieTitles;
        String[] movieDescriptions;
//        String[] movieGenres;
//        String[] movieCast;
        TypedArray movieImages;
        String[] movieTrailers;
        TypedArray movieCovers;


        // comment these out
        // ------------------------------------------------------------------
        movieTitles = getResources().getStringArray(R.array.movie_titles);
        movieDescriptions = getResources().getStringArray(R.array.movie_descriptions);
//        movieGenres[]
        movieImages = getResources().obtainTypedArray(R.array.movie_posters);
        movieTrailers = getResources().getStringArray(R.array.movie_trailers);
        movieCovers = getResources().obtainTypedArray(R.array.movie_background_covers);
        //  -----------------------------------------------------------------------


        //  -----------------------------------------------------------------------
        // put the Json data into an array
        // loop through the data and populate these arrays

//        for (movieData:movieList) {
//            movieTitles = movieData.;
//            movieDescriptions = movieData.;
//            movieGenres = movieData.;
//            movieCast = movieData.;
//            movieImages = movieData.;
//            movieTrailers = movieData.;
//            movieCovers = movieData.;
//        }

        //  -----------------------------------------------------------------------


        for (int i = 0; i<5; i++){
            listSlides.add(new Movie(movieTitles[i], movieDescriptions[i], getString(R.string.movie_genre), getString(R.string.cast), movieTrailers[i], movieImages.getResourceId(i, 0), movieCovers.getResourceId(i, 0)));
        }

        for (int i = 0; i<movieTitles.length; i++){
            listMovies.add(new Movie(movieTitles[i], movieDescriptions[i], getString(R.string.movie_genre), getString(R.string.cast), movieTrailers[i], movieImages.getResourceId(i, 0), movieCovers.getResourceId(i, 0)));
        }

    }

}