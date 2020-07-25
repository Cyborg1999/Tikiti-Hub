package com.drew.tikitihub.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.TypedArray;
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

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(HomeActivity.this, moviePoster, "sharedName");
        startActivity(viewMovieIntent, options.toBundle());

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

        // comment these variables and loops out
        // ------------------------------------------------------------------
        String[] movieTitles = getResources().getStringArray(R.array.movie_titles);
        String[] movieDescriptions = getResources().getStringArray(R.array.movie_descriptions);
        TypedArray movieImages = getResources().obtainTypedArray(R.array.movie_posters);
        String[] movieTrailers = getResources().getStringArray(R.array.movie_trailers);
        TypedArray movieCovers = getResources().obtainTypedArray(R.array.movie_background_covers);

        for (int i = 0; i<5; i++){
            listSlides.add(new Movie(movieTitles[i], movieCovers.getResourceId(i, 0)));
        }

        for (int i = 0; i<movieTitles.length; i++){
            listMovies.add(new Movie(movieTitles[i], movieDescriptions[i], getString(R.string.movie_genre), getString(R.string.cast), movieTrailers[i], movieImages.getResourceId(i, 0), movieCovers.getResourceId(i, 0)));
        }
        //  -----------------------------------------------------------------------


        // ------------------------------------------------------------------------
        // put the API data into a new list
        // use the loops below
        // replace the quotes with the appropriate data from the new list
        // ------------------------------------------------------------------------

        // Loop for the slides
        // for (int i = 0; i<5; i++){
        //     listSlides.add(new Movie("Title from API data", "Background Cover Image from API data"));
        // }

        // Loop for the new popular movies list
        // fix the condition the for loop
        // for (int i = 0; i<"array_name".length; i++){
        //      listMovies.add(new Movie("Title from API data", "Description from API data", "Genre from API data", "Cast from API data", "Trailer from API data", "Poster from API data", "Background Cover Image from API data"));
        // }

    }

}