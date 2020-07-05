package com.drew.tikitihub;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    // declare member variables - movie data and context
    private ArrayList<Movie> movieData;
    private Context myContext;

    // Create constructor to pass movie data and context
    public MovieAdapter(ArrayList<Movie> movieData, Context myContext) {
        this.movieData = movieData;
        this.myContext = myContext;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(myContext).inflate(R.layout.movie_list_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        //get current viewObject using its position and populate it with data
        Movie currentMovie = movieData.get(position);
        holder.bindTo(currentMovie);
    }

    @Override
    public int getItemCount() {
        return movieData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // declare private member variables
        private ImageView myMoviePoster;
        private TextView myMovieTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myMoviePoster = itemView.findViewById(R.id.mini_movie_poster);
            myMovieTitle = itemView.findViewById(R.id.mini_movie_title);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int moviePosition = getAdapterPosition();
                    Movie currentMovie = movieData.get(moviePosition);

                    Intent movieIntent = new Intent(myContext, ViewMovieActivity.class);
                    movieIntent.putExtra("movieTitle", currentMovie.getMovie_title());
                    movieIntent.putExtra("moviePoster", currentMovie.getMovie_poster());
                    myContext.startActivity(movieIntent);
                    Toast.makeText(myContext, "Showing " + currentMovie.getMovie_title(), Toast.LENGTH_SHORT).show();
                }
            });

        }

        public void bindTo(Movie currentMovie) {
            Glide.with(myContext).load(currentMovie.getMovie_poster()).into(myMoviePoster);
            myMovieTitle.setText(currentMovie.getMovie_title());
        }
    }
}
