package com.drew.tikitihub.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.drew.tikitihub.R;

public class MovieDescriptionFragment extends Fragment {

    public MovieDescriptionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_description, container, false);
        String description = getArguments().getString("description");
        TextView movieDescription = view.findViewById(R.id.movie_detail_description);
        movieDescription.setText(description);
        return view;
    }
}