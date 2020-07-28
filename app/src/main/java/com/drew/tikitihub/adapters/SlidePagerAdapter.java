package com.drew.tikitihub.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.drew.tikitihub.R;
import com.drew.tikitihub.models.Movie;

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
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View slideLayout = inflater.inflate(R.layout.slide_item, null);
        ImageView slideItemBackgroundCover = slideLayout.findViewById(R.id.slide_item_background_cover);
        TextView slideItemTitle = slideLayout.findViewById(R.id.slide_item_title);

        slideItemBackgroundCover.setImageResource(mList.get(position).getMovie_background_cover());
        slideItemTitle.setText(mList.get(position).getMovie_title());

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
