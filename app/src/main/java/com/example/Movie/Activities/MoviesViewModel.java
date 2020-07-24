package com.example.Movie.Activities;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Movie.Data.MoviesClient;
import com.example.Movie.Model.Movie;
import com.example.Movie.Model.Results;
import com.example.Movie.Util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MoviesViewModel extends ViewModel {
    MutableLiveData<List<Results>> movieMutableLiveData = new MutableLiveData<>();



    public void getMovies() {
        MoviesClient.getInstance().getMovies(Constants.API, "popularity.desc").enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                if (movie != null) {
                    List<Results> results = movie.getResults();
                    movieMutableLiveData.setValue(results);
                }

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
