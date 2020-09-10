package com.drew.tikitihub.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.drew.tikitihub.extra.Constant;
import com.drew.tikitihub.extra.MovieItemClickListener;
import com.drew.tikitihub.R;
import com.drew.tikitihub.models.Movie;

import java.util.ArrayList;
import java.util.List;

//public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
//
//    Context mContext;
//    List<Movie> mData;
//    MovieItemClickListener movieItemClickListener;
//
//    public MovieAdapter(Context mContext, List<Movie> mData, MovieItemClickListener listener) {
//        this.mContext = mContext;
//        this.mData = mData;
//        this.movieItemClickListener = listener;
//    }
//
//    @NonNull
//    @Override
//    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_item, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
//        holder.movieTitle.setText(mData.get(position).getMovie_title());
//        Glide.with(mContext).load(R.drawable.bad_boys_cover).into(holder.moviePoster);
//    }
//
//    @Override
//    public int getItemCount() {
//        return mData.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        private TextView movieTitle;
//        private ImageView moviePoster;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            movieTitle = itemView.findViewById(R.id.movie_item_title);
//            moviePoster = itemView.findViewById(R.id.movie_item_poster);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    movieItemClickListener.onMovieClick(mData.get(getAdapterPosition()), moviePoster);
//                }
//            });
//        }
//    }
//}

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder>{
        private Context context;
        private ArrayList<Movie> list;

    public MovieAdapter(Context context, ArrayList<Movie> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = list.get(position);
        holder.movieTitle.setText(movie.getMovie_title());
        Glide.with(context).load(Constant.URL+"storage/posters/"+movie.getMovie_poster()).into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder{
        private TextView movieTitle;
        private ImageView moviePoster;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movie_item_title);
            moviePoster = itemView.findViewById(R.id.movie_item_poster);
        }
    }
}
