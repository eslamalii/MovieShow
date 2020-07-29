package com.example.Movie.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.Movie.Model.Movie;
import com.example.Movie.Model.MovieDetailsObject;
import com.example.Movie.R;
import com.example.Movie.Util.Constants;
import com.example.Movie.ViewModel.DetailsViewModel;
import com.example.Movie.databinding.ActivityMovieDetailsBinding;
import com.squareup.picasso.Picasso;

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

    private DetailsViewModel detailsViewModel;

    ActivityMovieDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setUpUI();

        Intent intent = getIntent();
        int movieID = intent.getIntExtra("Movie", 0);

        detailsViewModel.getMovieDetails(movieID, Constants.API);

        detailsViewModel.detailsMutableLiveData.observe(this, new Observer<MovieDetailsObject>() {
            @Override
            public void onChanged(MovieDetailsObject movieDetailsObject) {
               setData(movieDetailsObject);
            }
        });

        detailsViewModel.busy.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.progressBar.setVisibility(integer);
            }
        });
    }

    private void setUpUI() {
        movieTitle = findViewById(R.id.movieNameID);
        coverImage = findViewById(R.id.coverImageID);
        posterImage = findViewById(R.id.posterImageID);
        movieYear = findViewById(R.id.yearReleaseID);
        director = findViewById(R.id.directedID);
        overview = findViewById(R.id.overviewID);
        runTime = findViewById(R.id.durationID);
        tagline = findViewById(R.id.tagLine);

    }

    private void setData(MovieDetailsObject movieDetailsObject){
        movieTitle.setText(movieDetailsObject.getTitle());
        movieYear.setText(movieDetailsObject.getRelease_date());
        overview.setText(movieDetailsObject.getOverview());
        runTime.setText(Integer.toString(movieDetailsObject.getRuntime()));
        tagline.setText(movieDetailsObject.getTagline());

        Picasso.get().load(Constants.IMAGE_URL + movieDetailsObject.getBackdrop_path()).into(coverImage);
        Picasso.get().load(Constants.IMAGE_URL + movieDetailsObject.getPoster_path()).into(posterImage);
    }
}
