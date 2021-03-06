package com.example.Movie.Activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Movie.Data.MovieRecyclerViewAdapter;
import com.example.Movie.Model.Results;
import com.example.Movie.R;
import com.example.Movie.Util.BroadcastReceiverCnn;
import com.example.Movie.Util.Constants;
import com.example.Movie.ViewModel.MoviesViewModel;
import com.example.Movie.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieRecyclerViewAdapter.OnMovieListener {

    private static final String TAG = "TEST";
    private RecyclerView recyclerView;
    private MoviesViewModel moviesViewModel;
    MovieRecyclerViewAdapter movieRecyclerViewAdapter;
    ActivityMainBinding binding;
    BroadcastReceiverCnn receiver = new BroadcastReceiverCnn();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        moviesViewModel.getMovies(Constants.API, "popularity.desc");

        setAdapter();

        moviesViewModel.movieMutableLiveData.observe(this, new Observer<List<Results>>() {
            @Override
            public void onChanged(List<Results> movies) {
                movieRecyclerViewAdapter.setMoviesList((ArrayList<Results>) movies);
            }
        });

        moviesViewModel.busy.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.progressBar.setVisibility(integer);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setAdapter() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        movieRecyclerViewAdapter = new MovieRecyclerViewAdapter(this);
        recyclerView.setAdapter(movieRecyclerViewAdapter);
        movieRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnMovieClick(int position) {
        Results results = movieRecyclerViewAdapter.getMoviesList().get(position);
        Intent intent = new Intent(this, MovieDetails.class);
        intent.putExtra("Movie", Integer.parseInt(results.getId().trim()));

        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }
}
