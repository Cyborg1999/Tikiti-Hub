package com.drew.tikitihub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.drew.tikitihub.R;
import com.drew.tikitihub.models.YoutubeConfig;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.Objects;

public class MovieTrailerActivity extends YouTubeBaseActivity {

    YouTubePlayerView mYoutubePlayerView;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;
    int currentTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_trailer);

        String movie_trailer = Objects.requireNonNull(getIntent().getExtras()).getString("movieTrailer");
        assert movie_trailer != null;
        String[] split = movie_trailer.split("v=");
        final String url = split[1];

        mYoutubePlayerView = (YouTubePlayerView) findViewById(R.id.movie_trailer_player);
        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(url, currentTime);
                youTubePlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
                    @Override
                    public void onPlaying() {
                    }

                    @Override
                    public void onPaused() {
                        currentTime = youTubePlayer.getCurrentTimeMillis();
                    }

                    @Override
                    public void onStopped() {
                        currentTime = youTubePlayer.getCurrentTimeMillis();
                    }

                    @Override
                    public void onBuffering(boolean b) {
                        currentTime = youTubePlayer.getCurrentTimeMillis();
                    }

                    @Override
                    public void onSeekTo(int i) {
                        currentTime = youTubePlayer.getCurrentTimeMillis();
                    }
                });

                youTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                    @Override
                    public void onFullscreen(boolean b) {
                        currentTime = youTubePlayer.getCurrentTimeMillis();
                        youTubePlayer.seekToMillis(currentTime);
                        youTubePlayer.play();
                    }
                });

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(), "An unexpected error has occurred", Toast.LENGTH_LONG).show();
            }
        };
        mYoutubePlayerView.initialize(YoutubeConfig.getApiKey(),mOnInitializedListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentTime = savedInstanceState.getInt("currentTime");
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("currentTime", currentTime);
    }
}