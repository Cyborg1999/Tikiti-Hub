package com.drew.tikitihub.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.drew.tikitihub.extra.Constant;
import com.drew.tikitihub.models.Movie;
import com.drew.tikitihub.adapters.MovieAdapter;
import com.drew.tikitihub.extra.MovieItemClickListener;
import com.drew.tikitihub.R;
import com.drew.tikitihub.adapters.SlidePagerAdapter;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity implements MovieItemClickListener {

    private ArrayList<Movie> listMovies, listSlides;
    private ViewPager slidePager;
    private RecyclerView nowShowingRecyclerView, comingSoonRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView txtAccount = findViewById(R.id.account_link);
        txtAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AccountActivity.class));
            }
        });

        slidePager = findViewById(R.id.slider_pager);
        TabLayout indicator = findViewById(R.id.slide_indicator);

        listSlides = new ArrayList<>();
        listMovies = new ArrayList<>();

        initializeData();

        // setup time
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new HomeActivity.SlideTimer(), 10000, 8000);
        indicator.setupWithViewPager(slidePager, true);

        // recycler view setup
        nowShowingRecyclerView = findViewById(R.id.now_showing_recycler_view);
        comingSoonRecyclerView = findViewById(R.id.coming_soon_recycler_view);

        nowShowingRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        nowShowingRecyclerView.setHasFixedSize(true);
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

        if (Build.VERSION.SDK_INT >= 21) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(HomeActivity.this, moviePoster, "sharedName");
            startActivity(viewMovieIntent, options.toBundle());
        } else {
            startActivity(viewMovieIntent);
        }

        Toast.makeText(this, movie.getMovie_title(), Toast.LENGTH_LONG).show();
    }

    class SlideTimer extends TimerTask {

        @Override
        public void run() {
            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (slidePager.getCurrentItem() < listSlides.size() - 1) {
                        slidePager.setCurrentItem(slidePager.getCurrentItem() + 1);
                    } else {
                        slidePager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    private void initializeData() {
        StringRequest request = new StringRequest(Request.Method.GET, Constant.MOVIES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("movies");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject movieObject = array.getJSONObject(i);
                        Movie movie = new Movie();
                        movie.setMovie_title(movieObject.getString("title"));
                        movie.setMovie_description(movieObject.getString("description"));
                        movie.setMovie_trailer(movieObject.getString("trailer"));
                        movie.setMovie_poster(movieObject.getString("poster"));
                        movie.setMovie_background_cover(movieObject.getString("cover_image"));
                        listMovies.add(movie);

                        if(i<5){
                            listSlides.add(movie);
                        }
                    }

                    MovieAdapter movieAdapter = new MovieAdapter(HomeActivity.this, listMovies, HomeActivity.this);
                    nowShowingRecyclerView.setAdapter(movieAdapter);
                    comingSoonRecyclerView.setAdapter(movieAdapter);

                    SlidePagerAdapter adapter = new SlidePagerAdapter(HomeActivity.this, listSlides);
                    slidePager.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("Tag", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeActivity.this, "Error: " + error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

}