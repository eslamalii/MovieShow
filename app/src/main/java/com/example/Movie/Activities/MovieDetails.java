package com.example.Movie.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.Movie.Model.Movie;
import com.example.Movie.Model.Results;
import com.example.Movie.R;

public class MovieDetails extends AppCompatActivity {

    private Movie movie;
    private TextView movieTitle;
    private ImageView coverImage;
    private ImageView posterImage;
    private TextView movieYear;
    private TextView runTime;
    private TextView director;
    private TextView overview;
    private TextView tagline;

    private String movieId;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setUpUI();

        Intent intent = getIntent();
        Results results = intent.getParcelableExtra("Movie Object");

        movieTitle.setText(results.getTitle());
        movieYear.setText(results.getRelease_date());
        overview.setText(results.getOverview());
        runTime.setText(results.getPopularity());
        tagline.setText(results.getAdult());
    }

    private void setUpUI () {
        movieTitle = findViewById(R.id.movieNameID);
        coverImage = findViewById(R.id.coverImageID);
        posterImage = findViewById(R.id.posterImageID);
        movieYear = findViewById(R.id.yearReleaseID);
        director = findViewById(R.id.directedID);
        overview = findViewById(R.id.overviewID);
        runTime = findViewById(R.id.durationID);
        tagline = findViewById(R.id.tagLine);

    }
}
