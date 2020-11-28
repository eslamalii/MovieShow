package com.example.Movie.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Movie.Data.CastRecyclerViewAdapter;
import com.example.Movie.Model.Cast;
import com.example.Movie.Model.Crew;
import com.example.Movie.Model.MovieDetailsObject;
import com.example.Movie.R;
import com.example.Movie.Util.Constants;
import com.example.Movie.ViewModel.CastViewModel;
import com.example.Movie.ViewModel.DetailsViewModel;
import com.example.Movie.databinding.ActivityMovieDetailsBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieDetails extends AppCompatActivity {

    private TextView movieTitle;
    private ImageView coverImage;
    private ImageView posterImage;
    private TextView movieYear;
    private TextView runTime;
    private TextView director;
    private TextView overview;
    private TextView tagline;

    private DetailsViewModel detailsViewModel;
    private CastViewModel castViewModel;

    private RecyclerView recyclerView;
    CastRecyclerViewAdapter castRecyclerViewAdapter;

    ActivityMovieDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
        castViewModel = new ViewModelProvider(this).get(CastViewModel.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setUpUI();
        setAdapter();

        Intent intent = getIntent();
        int movieID = intent.getIntExtra("Movie", 0);

        detailsViewModel.getMovieDetails(movieID, Constants.API);
        castViewModel.getMovieCast(movieID, Constants.API);

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

        castViewModel.castLiveData.observe(this, new Observer<List<Cast>>() {
            @Override
            public void onChanged(List<Cast> casts) {
                castRecyclerViewAdapter.setMovieCasting((ArrayList<Cast>) casts);
            }
        });

    }

    private void setUpUI() {
        movieTitle = findViewById(R.id.movieNameID);
        coverImage = findViewById(R.id.coverImageID);
        posterImage = findViewById(R.id.posterImageID);
        movieYear = findViewById(R.id.yearReleaseID);
        overview = findViewById(R.id.overviewID);
        runTime = findViewById(R.id.durationID);
        tagline = findViewById(R.id.tagLine);

    }

    private void setData(MovieDetailsObject movieDetailsObject) {
        movieTitle.setText(movieDetailsObject.getTitle());
        overview.setText(movieDetailsObject.getOverview());
        runTime.setText(Integer.toString(movieDetailsObject.getRuntime()));
        tagline.setText(movieDetailsObject.getTagline());
        movieYear.setText(movieDetailsObject.getRelease_date());

        Picasso.get().load(Constants.IMAGE_URL + movieDetailsObject.getBackdrop_path()).into(coverImage);
        Picasso.get().load(Constants.IMAGE_URL + movieDetailsObject.getPoster_path()).into(posterImage);
    }

    private void setAdapter(){
        recyclerView = findViewById(R.id.recyclerViewCast);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);

        castRecyclerViewAdapter = new CastRecyclerViewAdapter();
        recyclerView.setAdapter(castRecyclerViewAdapter);
        castRecyclerViewAdapter.notifyDataSetChanged();

    }

//    private void setColor(){
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.id.coverImageID);
//        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(@Nullable Palette palette) {
//                int vibrantColor = palette.getVibrantColor(R.color.primary_500);
//                collapsingToolbarLayout.set
//            }
//        });
//    }
}
