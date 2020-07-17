package com.example.Movie.Activities;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.example.Movie.Data.MovieRecyclerViewAdapter;
import com.example.Movie.Model.Movie;
import com.example.Movie.Model.Results;
import com.example.Movie.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Movie> movieList;
    private RequestQueue queue;
    private ProgressBar progressBar;
    private MoviesViewModel moviesViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

//        queue = Volleysingleton.getInstance(this).getRequestQueue();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        moviesViewModel.getMovies();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
//
//        movieList = new ArrayList<>();
//
//        movieList = getMovies();

        final MovieRecyclerViewAdapter movieRecyclerViewAdapter = new MovieRecyclerViewAdapter();
        recyclerView.setAdapter(movieRecyclerViewAdapter);
        movieRecyclerViewAdapter.notifyDataSetChanged();


        moviesViewModel.movieMutableLiveData.observe(this, new Observer<List<Results>>() {
            @Override
            public void onChanged(List<Results> movies) {
                movieRecyclerViewAdapter.setMoviesList((ArrayList<Results>) movies);
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

//    public List<Movie> getMovies() {
//        movieList.clear();
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
//                Constants.URL_LEFT + Constants.API + Constants.URL_RIGHT, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    JSONArray moviesArray = response.getJSONArray("results");
//
//                    for (int i = 0; i < moviesArray.length(); i++) {
//                        JSONObject object = moviesArray.getJSONObject(i);
//
//                        Movie movie = new Movie();
//                        movie.setPoster(object.getString("poster_path"));
//                        movie.setImdbId(object.getString("id"));
//                        movie.setTitle(object.getString("title"));
//                        movie.setPlot(object.getString("overview"));
//
//                        movieList.add(movie);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this, "That didn't work!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        queue.add(jsonObjectRequest);
//
//        return movieList;
//
//    }


}
