package com.drew.tikitihub.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.drew.tikitihub.extra.MovieItemClickListener;
import com.drew.tikitihub.R;
import com.drew.tikitihub.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context mContext;
    List<Movie> mData;
    MovieItemClickListener movieItemClickListener;

    public MovieAdapter(Context mContext, List<Movie> mData, MovieItemClickListener listener) {
        this.mContext = mContext;
        this.mData = mData;
        this.movieItemClickListener = listener;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        holder.movieTitle.setText(mData.get(position).getMovie_title());
        holder.moviePoster.setImageResource(mData.get(position).getMovie_poster());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView movieTitle;
        private ImageView moviePoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movieTitle = itemView.findViewById(R.id.movie_item_title);
            moviePoster = itemView.findViewById(R.id.movie_item_poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieItemClickListener.onMovieClick(mData.get(getAdapterPosition()), moviePoster);
                }
            });
        }
    }
}
