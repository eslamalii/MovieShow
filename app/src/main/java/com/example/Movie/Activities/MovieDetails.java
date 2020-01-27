package com.example.Movie.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.Movie.Model.Movie;
import com.example.Movie.R;

public class MovieDetails extends AppCompatActivity {

    private Movie movie;
    private TextView movieTitle;
    private ImageView coverImage;
    private ImageView posterImage;
    private TextView movieYear;
    private TextView director;
    private TextView overview;

    private RequestQueue queue;
    private String movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movie = (Movie) getIntent().getSerializableExtra("movie");
        movieId = movie.getImdbId();

        setUpUI();
        getMoviesDetails(movieId);
    }

    private void setUpUI() {
        movieTitle = findViewById(R.id.movieNameID);
        coverImage = findViewById(R.id.coverImageID);
        posterImage = findViewById(R.id.posterImageID);
        movieYear = findViewById(R.id.yearReleaseID);
        director = findViewById(R.id.directedID);
        overview = findViewById(R.id.overviewID);
    }
}
