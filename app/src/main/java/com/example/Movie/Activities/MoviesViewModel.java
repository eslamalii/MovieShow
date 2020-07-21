package com.example.Movie.Activities;

import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Movie.BR;
import com.example.Movie.Data.MoviesClient;
import com.example.Movie.Model.Movie;
import com.example.Movie.Model.Results;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesViewModel extends ViewModel {
    MutableLiveData<List<Results>> movieMutableLiveData = new MutableLiveData<>();

    public void getMovies() {
        MoviesClient.getInstance().getMovies().enqueue(new Callback<Movie>() {
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

            }
        });
    }
}
