package com.drew.tikitihub.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.drew.tikitihub.R;
import com.drew.tikitihub.models.Movie;
import com.drew.tikitihub.ui.MovieDetailActivity;
import com.drew.tikitihub.ui.MovieTrailerActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class SlidePagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<Movie> mList;

    public SlidePagerAdapter(Context mContext, List<Movie> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View slideLayout = inflater.inflate(R.layout.slide_item, null);
        ImageView slideItemBackgroundCover = slideLayout.findViewById(R.id.slide_item_background_cover);
        TextView slideItemTitle = slideLayout.findViewById(R.id.slide_item_title);
        FloatingActionButton slideItemPlayBtn = slideLayout.findViewById(R.id.slide_play_button);

        slideItemBackgroundCover.setImageResource(mList.get(position).getMovie_background_cover());
        slideItemTitle.setText(mList.get(position).getMovie_title());

        slideLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewMovieIntent = new Intent(mContext, MovieDetailActivity.class);
                viewMovieIntent.putExtra("movieTitle", mList.get(position).getMovie_title());
                viewMovieIntent.putExtra("movieDescription", mList.get(position).getMovie_description());
                viewMovieIntent.putExtra("movieGenre", mList.get(position).getMovie_genre());
                viewMovieIntent.putExtra("movieCast", mList.get(position).getMovie_cast());
                viewMovieIntent.putExtra("moviePoster", mList.get(position).getMovie_poster());
                viewMovieIntent.putExtra("movieTrailer", mList.get(position).getMovie_trailer());
                viewMovieIntent.putExtra("movieBackgroundCover", mList.get(position).getMovie_background_cover());
                mContext.startActivity(viewMovieIntent);
            }
        });

        slideItemPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewMovieTrailerIntent = new Intent(mContext, MovieTrailerActivity.class);
                viewMovieTrailerIntent.putExtra("movieTrailer", mList.get(position).getMovie_trailer());
                mContext.startActivity(viewMovieTrailerIntent);
            }
        });

        container.addView(slideLayout);
        return slideLayout;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
