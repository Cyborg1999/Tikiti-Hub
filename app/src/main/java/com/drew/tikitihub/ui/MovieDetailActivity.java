package com.drew.tikitihub.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.drew.tikitihub.extra.MovieDetailViewPager;
import com.drew.tikitihub.R;
import com.drew.tikitihub.adapters.MovieDetailAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MovieDetailActivity extends AppCompatActivity {

    Button bookMovieBtn;
    Dialog bookMoviePopup;
    ImageView bookMoviePoster;
    TextView bookMovieTitle, bookMovieGenres, bookMovieDescription;
    Button bookMovieCheckout;
    ProgressBar bookMovieProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initViews();
    }

    public void initViews(){
        // Get intent data
        String movie_title = getIntent().getExtras().getString("movieTitle");
        String movie_description = getIntent().getExtras().getString("movieDescription");
        String movie_genre = getIntent().getExtras().getString("movieGenre");
        String movie_cast = getIntent().getExtras().getString("movieCast");
        int movie_poster_source = getIntent().getExtras().getInt("moviePoster");
        int movie_background_cover_source = getIntent().getExtras().getInt("movieBackgroundCover");

        // initialize variables
        ImageView moviePoster = findViewById(R.id.movie_detail_poster);
        ImageView movieCoverImage = findViewById(R.id.movie_detail_background_cover);
        TextView movieTitle = findViewById(R.id.movie_detail_title);
        TextView movieGenre = findViewById(R.id.movie_detail_genres);
        FloatingActionButton play_fab = findViewById(R.id.play_trailer);

        setupAnimation(movieCoverImage, play_fab);

        //set values
        Glide.with(this).load(movie_background_cover_source).into(movieCoverImage);
        Glide.with(this).load(movie_poster_source).into(moviePoster);
        movieTitle.setText(movie_title);
        movieGenre.setText(movie_genre);

        TabLayout tabLayout = findViewById(R.id.movie_detail_tabs);
        MovieDetailViewPager viewPager = findViewById(R.id.movie_details_pager);

        setupTabsAndViewPager(movie_description, movie_cast, viewPager, tabLayout);

        bookMovieBtn = findViewById(R.id.book_ticket_button);
        setupBookMoviePopup(movie_title, movie_genre, movie_description, movie_poster_source);
    }

    private void setupBookMoviePopup(String movie_title, String movie_genre, String movie_description, int movie_poster_source) {

        bookMoviePopup = new Dialog(this);
        bookMoviePopup.setContentView(R.layout.popup_book_movie);
        bookMoviePopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        bookMoviePopup.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
        bookMoviePopup.getWindow().getAttributes().gravity = Gravity.TOP;

        bookMovieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookMoviePopup.show();
            }
        });

        bookMovieTitle = bookMoviePopup.findViewById(R.id.book_movie_title);
        bookMovieGenres = bookMoviePopup.findViewById(R.id.book_movie_genres);
        bookMovieDescription = bookMoviePopup.findViewById(R.id.book_movie_description);
        bookMoviePoster = bookMoviePopup.findViewById(R.id.book_movie_poster);
        bookMovieCheckout = bookMoviePopup.findViewById(R.id.book_movie_checkout);
        bookMovieProgress = bookMoviePopup.findViewById(R.id.book_movie_checkout_progress);

        bookMovieTitle.setText(movie_title);
        bookMovieGenres.setText(movie_genre);
        bookMovieDescription.setText(movie_description);
        Glide.with(this).load(movie_poster_source).into(bookMoviePoster);

        //set up spinner and date picker

        bookMovieCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookMovieCheckout.setVisibility(View.INVISIBLE);
                bookMovieProgress.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setupTabsAndViewPager(String movie_description, String movie_cast, MovieDetailViewPager viewPager, TabLayout tabLayout) {
        // pass movie description and cast info to fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        MovieDescriptionFragment movieDescriptionFragment = new MovieDescriptionFragment();
        MovieCastFragment movieCastFragment = new MovieCastFragment();

        Bundle descriptionBundle = new Bundle();
        descriptionBundle.putString("description", movie_description);

        Bundle castBundle = new Bundle();
        castBundle.putString("cast", movie_cast);

        movieDescriptionFragment.setArguments(descriptionBundle);
        movieCastFragment.setArguments(castBundle);

        // setup adapter
        MovieDetailAdapter movieDetailAdapter = new MovieDetailAdapter(getSupportFragmentManager());
        movieDetailAdapter.addFragment(movieDescriptionFragment, "Movie Info");
        movieDetailAdapter.addFragment(movieCastFragment, "Cast");

        viewPager.setAdapter(movieDetailAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setupAnimation(ImageView movieCoverImage, FloatingActionButton play_fab){
        // set transition animation
        movieCoverImage.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
    }
}