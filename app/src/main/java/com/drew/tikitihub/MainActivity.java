package com.drew.tikitihub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movieData;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // declare member variables
        RecyclerView nowShowingRecyclerView = findViewById(R.id.now_showing_recyclerview);
        nowShowingRecyclerView.setHasFixedSize(true);
        movieData = new ArrayList<>();
        initializeData();
        movieAdapter = new MovieAdapter(movieData, this);
        nowShowingRecyclerView.setAdapter(movieAdapter);
        nowShowingRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

    }

    private void initializeData() {
        String[] movieTitles = getResources().getStringArray(R.array.movie_titles);
        TypedArray movieImages = getResources().obtainTypedArray(R.array.movie_posters);

        for (int i = 0; i<movieTitles.length; i++){
            movieData.add(new Movie(movieImages.getResourceId(i, 0), movieTitles[i]));
        }
    }
}
