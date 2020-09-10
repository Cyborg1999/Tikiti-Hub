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
    private SharedPreferences sharedPreferences;
    private JSONArray movies;

    private ArrayList<String> movieTitles;
    private ArrayList<String> movieDescriptions;
    //        String[] movieGenres;
//        String[] movieCast;
    private ArrayList<String> movieImages;
    private ArrayList<String> movieTrailers;
    private ArrayList<String> movieCovers;

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
//        SlidePagerAdapter adapter = new SlidePagerAdapter(this, listSlides);
//        slidePager.setAdapter(adapter);
        //setup time
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new HomeActivity.SlideTimer(), 10000, 8000);
//        indicator.setupWithViewPager(slidePager, true);

        // recycler view setup
        RecyclerView nowShowingRecyclerView = findViewById(R.id.now_showing_recycler_view);
        RecyclerView comingSoonRecyclerView = findViewById(R.id.coming_soon_recycler_view);
        MovieAdapter movieAdapter = new MovieAdapter(this, listMovies);
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
        sharedPreferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        StringRequest request = new StringRequest(Request.Method.GET, Constant.MOVIES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            if(object.getBoolean("success")){
                                movies = new JSONArray(object.getString("movies"));
                                for(int i =0; i<movies.length(); i++){
                                    JSONObject movieObject = movies.getJSONObject(i);
                                    Movie movie = new Movie(movieObject.getString("title"), movieObject.getString("description"), "", "", movieObject.getString("trailer"), movieObject.getString("poster"), movieObject.getString("cover_image")) {
                                    };
                                    listMovies.add(movie);
                                    Log.e("Response", "movies:" +movie);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Response", error.toString());
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = sharedPreferences.getString("token", "");
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                params.put("Authorization", "Bearer "+token);
                return params;
            }
        };

//        for (int i = 0; i<5; i++){
//            listSlides.add(new Movie(movieTitles.get(i), movieDescriptions.get(i), "", "", movieTrailers.get(i), movieImages.get(i), movieCovers.get(i)));
//        }

    }

}