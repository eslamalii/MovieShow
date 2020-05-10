package com.example.Movie.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.Movie.Model.Movie;
import com.example.Movie.R;
import com.example.Movie.Util.Constants;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

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

    private RequestQueue queue;
    private String movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        queue = Volley.newRequestQueue(this);

        movie = (Movie) getIntent().getSerializableExtra("movie");
        movieId = movie.getImdbId();


        setUpUI();
        getMoviesDetails(movieId);
    }

    private void getMoviesDetails(String id) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.URL_MOVIE + id + Constants.API, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    movieTitle.setText(response.getString("title"));
                    movieYear.setText(response.getString("release_date"));
                    runTime.setText(response.getString("runtime") + "mins");
                    overview.setText(response.getString("overview"));
                    tagline.setText(response.getString("tagline"));

                    Picasso.get()
                            .load("https://image.tmdb.org/t/p/w500" + response.getString("poster_path"))
                            .into(posterImage);

                    Picasso.get()
                            .load("https://image.tmdb.org/t/p/w500" + response.getString("backdrop_path"))
                            .into(coverImage);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: " + error.getMessage());
            }
        });
        queue.add(jsonObjectRequest);
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
}
